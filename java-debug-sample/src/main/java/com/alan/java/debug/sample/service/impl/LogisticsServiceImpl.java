package com.alan.java.debug.sample.service.impl;

import com.alan.java.debug.sample.pojo.Order;
import com.alan.java.debug.sample.service.LogisticsService;
import com.alan.java.debug.sample.util.TimeWatch;
import org.springframework.stereotype.Service;

@Service
public class LogisticsServiceImpl implements LogisticsService {

    @Override
    public void deliver(Order order) {
        TimeWatch.mockElapse(100, 5000);
    }
}
