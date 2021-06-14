package com.alan.java.debug.sample.service.impl;

import com.alan.java.debug.sample.pojo.Order;
import com.alan.java.debug.sample.service.LogisticsService;
import com.alan.java.debug.sample.service.OrderService;
import com.alan.java.debug.sample.util.TimeWatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.alan.java.debug.sample.util.TimeWatch.mockElapse;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private LogisticsService logisticsService;

    @Override
    public Boolean deliver(Long id) {
        TimeWatch timeWatch = new TimeWatch(log);

        // 检查权限
        checkPrivilege();

        timeWatch.log("check privilege");

        // 检查订单是否存在
        Order order = checkOrder(id);

        timeWatch.log("check order");

        // 更新订单状态为发货中
        updateOrder(order);

        timeWatch.log("update status to delivering");

        // 调用物流接口发货
        logisticsService.deliver(order);

        timeWatch.log("remote call logistics deliver api");

        // 更新订单状态为发货完成
        updateOrder(order);

        timeWatch.log("update status to delivered");

        timeWatch.logTotal("finished one order delivering");

        return true;
    }

    private void updateOrder(Order order) {
        mockElapse(5, 20);
    }

    private Order checkOrder(Long id) {
        Order order = new Order();
        order.setId(id);

        mockElapse(15, 100);

        return order;
    }

    private void checkPrivilege() {
        mockElapse(10, 200);
    }
}
