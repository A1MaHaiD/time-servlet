package com.goit.server;

import com.goit.exception.ServerInternalErrorException;
import com.goit.exception.ServerNotFoundException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeServerHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        URI endpoint = exchange.getRequestURI();
        sendResponse(exchange, setTime(), 200);
        String responseBody = "";
        int httpStatus = 200;
        try {
            switch (requestMethod) {
                case "GET" -> responseBody = handleGET(exchange, httpStatus);
                case "POST" -> responseBody = handlePOST(exchange, httpStatus);
            }
        } catch (Exception e) {
            if (e instanceof ServerNotFoundException) {
                httpStatus = ((ServerInternalErrorException) e).getHttpCode();
                responseBody = e.getMessage();
            }
        }
        sendResponse(exchange, setTime(), httpStatus);
    }

    private String handlePOST(HttpExchange exchange, int httpStatus) {
        return "";
    }

    private String handleGET(HttpExchange exchange, int httpStatus) {

        return "";
    }

    private void sendResponse(HttpExchange exchange, String response, int rCode) throws IOException {
        exchange.sendResponseHeaders(rCode, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private String setTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss 'UTC'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTF"));
        String formattedTime = formatter.format(new Date());
        return formattedTime;
    }
}
