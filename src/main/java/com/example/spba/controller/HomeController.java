package com.example.spba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {

    @GetMapping("/")
    public void redirectRoot(HttpServletResponse response) throws IOException {
        // 302 重定向到 /spba-api/home/index.html
        response.sendRedirect("/spba-api/home/index.html");
    }

    @GetMapping("/spba-api/")
    public void redirectSpbaRoot(HttpServletResponse response) throws IOException {
        // 302 重定向到 /spba-api/home/index.html
        response.sendRedirect("/spba-api/home/index.html");
    }

    // 承接 Tomcat 错误页转发过来的 /home/index.html
    @GetMapping("/home/index.html")
    public void redirectSpbaHomeRoot(HttpServletResponse response) throws IOException {
        // 这里可以再做一次显式重定向（或直接返回静态页）
        // 如果是静态页，确保文件在 resources/static/home/index.html
        response.sendRedirect("/spba-api/home/index.html");
    }
}