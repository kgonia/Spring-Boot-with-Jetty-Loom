package org.eclipse.jetty.loom;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.io.IOException;

public class HelloHandler extends AbstractHandler
{

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");

        response.getOutputStream().println("Hello");

        baseRequest.setHandled(true);
    }
}
