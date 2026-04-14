package com.example.spba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpbaApiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SpbaApiApplication.class, args);
        System.out.println("http://localhost:8082/spba-api/home");
        System.out.println("http://192.168.11.31:8082/spba-api/home");
        System.out.println("http://vincentxxx0327.51vip.biz/spba-api/home");
    }
}