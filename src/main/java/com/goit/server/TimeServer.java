package com.goit.server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class TimeServer {
    HttpServer server;

    public TimeServer(int port, String path, int nThreads) throws IOException {
        InetSocketAddress localhost = new InetSocketAddress(port);
        HttpServer httpServer = HttpServerProvider.provider().createHttpServer(localhost, 0);
        httpServer.createContext(path, new TimeServerHandler());
        httpServer.setExecutor(Executors.newFixedThreadPool(nThreads));
        server = httpServer;
    }

    public void start() {
        server.start();
        System.out.println("Server started");
    }

    public void stop() {
        server = null;
    }
}