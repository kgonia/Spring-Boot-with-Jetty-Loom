package org.eclipse.jetty.loom;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.LifeCycle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ServerWithLoomTest
{
    private Server server;

    @BeforeEach
    public void startUp() throws Exception
    {
        server = ServerWithLoom.createServer(0);
        server.start();
    }

    @AfterEach
    public void tearDown()
    {
        LifeCycle.stop(server);
    }

    @Test
    public void testSingleRequest() throws IOException
    {
        HttpURLConnection http = (HttpURLConnection)server.getURI().resolve("/").toURL().openConnection();
        assertThat("Http Status", http.getResponseCode(), is(HttpURLConnection.HTTP_OK));
    }

    @Test
    public void testManyRequest(TestInfo testInfo) throws IOException, InterruptedException
    {
        int taskCount = 2000;

        URL url = server.getURI().resolve("/").toURL();

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            List<Callable<Integer>> tasks = new ArrayList<>();
            for (int i = 0; i < taskCount; i++) {
                tasks.add(() ->
                {
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    return http.getResponseCode();
                });
            }

            executorService.invokeAll(tasks);
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
