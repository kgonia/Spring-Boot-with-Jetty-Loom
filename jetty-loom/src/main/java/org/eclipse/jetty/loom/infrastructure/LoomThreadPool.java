package org.eclipse.jetty.loom.infrastructure;

import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.thread.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class LoomThreadPool extends AbstractLifeCycle implements ThreadPool
{
    private final ExecutorService executorService;

    public LoomThreadPool()
    {
        this(false);
    }

    public LoomThreadPool(boolean inheritInheritableThreadLocals) {
        ThreadFactory factory = Thread.ofPlatform()
                .allowSetThreadLocals(true)
                .inheritInheritableThreadLocals(inheritInheritableThreadLocals)
                .name("LoomThreadPool-", 0)
                .factory();;
        executorService = Executors.newThreadPerTaskExecutor(factory);
    }

    @Override
    public void execute(Runnable command)
    {
        executorService.submit(command);
    }

    @Override
    public void join()
    {
        while (!executorService.isTerminated())
        {
            Thread.onSpinWait();
        }
    }

    @Override
    protected void doStop() throws Exception
    {
        super.doStop();
        executorService.shutdown();
    }

    @Override
    public int getThreads()
    {
        // TODO: always report a value?
        return Integer.MAX_VALUE;
    }

    @Override
    public int getIdleThreads()
    {
        // TODO: always report available?
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isLowOnThreads()
    {
        return false;
    }
}
