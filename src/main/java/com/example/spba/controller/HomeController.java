package com.example.spba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {

    @GetMapping("/spba-api/")
    public void redirectSpbaRoot(HttpServletResponse response) throws IOException {
        // 302 重定向到 /spba-api/home/index.html
        response.sendRedirect("/spba-api/home/index.html");
    }

}