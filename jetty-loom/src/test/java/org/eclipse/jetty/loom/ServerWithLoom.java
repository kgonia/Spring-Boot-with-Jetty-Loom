package org.eclipse.jetty.loom;

import org.eclipse.jetty.http.HttpGenerator;

import org.eclipse.jetty.loom.infrastructure.LoomThreadPool;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.util.Jetty;

public class ServerWithLoom
{
    public static Server createServer(int port)
    {
        LoomThreadPool loomThreadPool = new LoomThreadPool();
        Server server = new Server(loomThreadPool);

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
            HttpGenerator.setJettyVersion("Jetty(Loom)-" + Jetty.VERSION);
            server.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
