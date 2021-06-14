package com.alan.java.debug.sample.controller;

import com.alan.java.debug.sample.pojo.Result;
import com.alan.java.debug.sample.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单发货
     *
     * @param id
     * @return
     */
    @GetMapping("/deliver/{id}")
    public Result<Boolean> deliver(@PathVariable Long id) {
        MDC.put("orderId", String.valueOf(id));
        MDC.put("action", "发货");
        log.info("receive one order to deliver");
        return new Result<>(orderService.deliver(id));
    }
}
