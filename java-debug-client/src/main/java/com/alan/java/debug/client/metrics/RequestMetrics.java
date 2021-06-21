package com.alan.java.debug.client.metrics;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestMetrics {

    private static final Map<String, AtomicInteger> map = new ConcurrentHashMap<>();

    public static void increment(String path) {
        synchronized (RequestMetrics.class) {
            AtomicInteger atomicInteger = map.getOrDefault(path, new AtomicInteger(0));
            map.put(path, atomicInteger);
        }
        map.get(path).incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                increment("/order/deliver");
            }
            countDownLatch.countDown();
        }).start();

        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                increment("/order/deliver");
            }
            countDownLatch.countDown();
        }).start();

        countDownLatch.await();

        System.out.println(map);
    }
}
