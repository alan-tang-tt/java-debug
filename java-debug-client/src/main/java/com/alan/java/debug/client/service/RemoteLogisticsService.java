package com.alan.java.debug.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "remoteLogisticsService", url = "http://java-debug-server")
public interface RemoteLogisticsService {

    @PostMapping("/logistics/deliver/{orderId}")
    String deliver(@PathVariable("orderId") Long orderId);
}
