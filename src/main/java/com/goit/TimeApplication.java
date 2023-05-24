package com.goit;

import com.goit.server.TimeServer;
import com.goit.servlet.time.TimeServlet;

import java.io.IOException;

public class TimeApplication {
    public static void main(String[] args) throws IOException {
        TimeServlet timeServlet = new TimeServlet();
        TimeServer timeServer = new TimeServer(8080, "/", 4);
        timeServer.start();
    }
}
