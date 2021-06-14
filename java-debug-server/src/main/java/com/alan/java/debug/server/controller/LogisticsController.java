package com.alan.java.debug.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogisticsController {

    @PostMapping("/logistics/deliver/{orderId}")
    public String deliver(@PathVariable Long orderId) {
        log.info("hello: {}", orderId);
        return "success: " + orderId;
    }
}
