package com.example.spba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Controller
public class AdminController {

    private static final String AUTH_COOKIE = "_spba_auth";
    private static final long SIX_HOURS_MS = 6 * 60 * 60 * 1000;

    @GetMapping("/backend")
    public void adminEntry(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authCookie = getCookieValue(request, AUTH_COOKIE);
        if (authCookie != null && isWithinSixHours(authCookie)) {
            response.sendRedirect("/spba-api/backend/welcome.html");
        } else {
            response.sendRedirect("/spba-api/backend/login.html");
        }
    }

    private String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private boolean isWithinSixHours(String encoded) {
        try {
            String decoded = new String(Base64.getDecoder().decode(encoded));
            long loginTime = Long.parseLong(decoded);
            return (System.currentTimeMillis() - loginTime) < SIX_HOURS_MS;
        } catch (Exception e) {
            return false;
        }
    }
}
