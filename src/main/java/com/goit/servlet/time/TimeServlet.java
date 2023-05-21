package com.goit.servlet.time;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    private static final String TIME_ZONE_HEADER = "timezone";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String timezoneParam = req.getParameter(TIME_ZONE_HEADER);
        TimeZone timeZone;
        if (timezoneParam != null && !timezoneParam.isEmpty()) {
            timeZone = TimeZone.getTimeZone(timezoneParam);
        } else {
            timeZone = TimeZone.getTimeZone("UTC");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss 'UTC'");
        formatter.setTimeZone(timeZone);
        String formattedTime = formatter.format(new Date());
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(formattedTime);
        resp.getWriter().close();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}