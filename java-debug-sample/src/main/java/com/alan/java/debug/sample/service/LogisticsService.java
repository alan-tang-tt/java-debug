package com.alan.java.debug.sample.service;


import com.alan.java.debug.sample.pojo.Order;

public interface LogisticsService {

    /**
     * 物流发货
     * @param order
     */
    void deliver(Order order);

}
