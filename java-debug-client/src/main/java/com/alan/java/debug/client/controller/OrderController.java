package com.alan.java.debug.client.controller;

import com.alan.java.debug.client.metrics.RequestMetrics;
import com.alan.java.debug.client.service.RemoteLogisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    private RemoteLogisticsService remoteLogisticsService;

    @PostMapping("/order/deliver/{orderId}")
    public String deliver(@PathVariable Long orderId) {
        RequestMetrics.increment("/order/deliver");
        log.info("receive request: {}", orderId);
        return remoteLogisticsService.deliver(orderId);
    }
}
