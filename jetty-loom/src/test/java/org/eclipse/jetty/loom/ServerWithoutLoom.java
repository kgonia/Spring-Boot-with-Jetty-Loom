package org.eclipse.jetty.loom;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class ServerWithoutLoom
{
    public static Server createServer(int port)
    {
        QueuedThreadPool qtp = new QueuedThreadPool(64);
        Server server = new Server(qtp);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);

        HandlerList handlers = new HandlerList();
        handlers.addHandler(new HelloHandler());
        handlers.addHandler(new DefaultHandler());

        server.setHandler(handlers);
        return server;
    }

    public static void main(String[] args)
    {
        try
        {
            Server server = createServer(8888);
            server.start();
            server.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
