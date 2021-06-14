package com.alan.java.debug.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JavaDebugClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaDebugClientApplication.class, args);
    }

}
