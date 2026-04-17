package com.example.spba.config;

import com.example.spba.interceptor.SpbaInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Bean
    public SpbaInterceptor spbaInterceptor() {
        return new SpbaInterceptor();
    }

    /**
     * 视图控制器映射
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/home", "/home/index.html");
        registry.addRedirectViewController("/home/index", "/home/index.html");
        registry.addRedirectViewController("/backend", "/backend/welcome.html");
        registry.addRedirectViewController("/static", "/home/index.html");
        registry.addRedirectViewController("/image", "/home/index.html");
    }

    /**
     * 拦截器
     * addPathPatterns 用于添加拦截规则
     * excludePathPatterns 用于排除拦截
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(spbaInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }
}
