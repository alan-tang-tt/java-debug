package com.alan.java.debug.sample.content;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 线程池中keepAliveTime的作用?
 * 1. 控制非核心线程的超时，让它们自然消亡
 * 2. 如果开启了允许核心线程超时，它也可以控制核心线程的消亡
 *
 * 原理是什么呢？
 * 1. 通过控制线程池从阻塞队列中拿任务的方式来控制，如果允许超时（wc>max || allowCoreTimeout）,调用bq.poll(keepAliveTime, unit)
 * 如果不允许超时，它就调用bq.take();
 * 线程池中keepAliveTime的实现原理是通过BlockingQueue来控制的。
 * 2. bq.poll(keepAliveTime, unit)超时之后会返回一个null，这时候线程池这个线程就超时了，下一次循环的时候返回的任务就为null
 * 3. runWorker()方法里面判断如果task == null，那么它就会跳出循环，相当于自然销毁这个线程
 *
 */
public class ThreadPoolTest {

    private static final ThreadPoolExecutor THREAD_POOL =
            new ThreadPoolExecutor(
                    4,
                    8,
                    1,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(10));

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 18; i++) {
            THREAD_POOL.execute(()->{
                // 每个任务等一秒
                LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
                System.out.println(1);
            });
        }

        System.in.read();
    }
}
