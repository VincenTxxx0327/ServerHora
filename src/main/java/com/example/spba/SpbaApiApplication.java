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
    }
}