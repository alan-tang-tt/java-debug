package com.alan.java.debug.sample.content;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 多线程调试法
 *
 * 实现一个生产者消费者模式，并说说它的原理？
 *
 * 疑问：
 *      1. 如果队列为空，这时候我去取数据，它怎么工作的？
 *      2. 如果队列为满，这时候我还往里面放数据，它是怎么工作的？
 *
 * BLockingQueue
 * ConcurrentLinkedQueue
 *
 */
public class MultiThreadTest {

    private static final BlockingQueue<Integer> BLOCKING_QUEUE = new ArrayBlockingQueue<>(100);

    private void producer() {
        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(() -> {
                try {
                    for (int j = 0; j < 100; j++) {
                        BLOCKING_QUEUE.put(num * 100 + j);
                    }
                    System.out.println("produce finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "producer-" + i).start();
        }
    }

    private void consumer() {
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        Integer data = BLOCKING_QUEUE.take();
                        System.out.println(data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "consumer-" + i).start();
        }
    }

    public static void main(String[] args) throws IOException {
        MultiThreadTest test = new MultiThreadTest();
        test.producer();
        test.consumer();

        System.in.read();
    }


}
