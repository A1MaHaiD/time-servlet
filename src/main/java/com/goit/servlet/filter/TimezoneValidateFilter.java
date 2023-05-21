package com.goit.servlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.TimeZone;

@WebFilter("/time")
public class TimezoneValidateFilter extends HttpFilter {
    private static final String TIME_ZONE_HEADER = "timezone";

    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String timezoneParam = request.getParameter(TIME_ZONE_HEADER);
        if (timezoneParam == null || timezoneParam.isEmpty() || !isValidTimezone(timezoneParam)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("text/plain");
            String message = "Invalid timezone";
            response.getWriter().write(message);
        }
        chain.doFilter(request, response);
    }

    private boolean isValidTimezone(String timezone) {
        String[] availableTimezones = TimeZone.getAvailableIDs();
        for (String availableTimezone : availableTimezones) {
            if (availableTimezone.equals(timezone)) {
                return true;
            }
        }
        return false;
    }
}
