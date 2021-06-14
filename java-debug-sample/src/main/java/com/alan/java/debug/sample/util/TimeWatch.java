package com.alan.java.debug.sample.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class TimeWatch {
    private long start;
    private long last;
    private Logger logger;

    public TimeWatch(Logger logger) {
        this.start = this.last = System.currentTimeMillis();
        this.logger = logger;
    }

    public void log(String content, Object... objs) {
        long now = System.currentTimeMillis();

        Object[] newObjs = new Object[objs.length + 1];
        System.arraycopy(objs, 0, newObjs, 0, objs.length);
        newObjs[objs.length] = now - this.last;

        this.last = now;

        logger.info(content + ", elapse={}", newObjs);
    }

    public void logTotal(String content, Object... objs) {
        long now = System.currentTimeMillis();

        Object[] newObjs = new Object[objs.length + 1];
        System.arraycopy(objs, 0, newObjs, 0, objs.length);
        newObjs[objs.length] = now - this.start;

        this.last = now;

        logger.info(content + ", elapseTotal={}", newObjs);
    }

    /**
     * 模拟耗时
     * @param low
     * @param high
     */
    public static void mockElapse(int low, int high) {
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(ThreadLocalRandom.current().nextInt(low, high)));
    }

    public static void main(String[] args) {
        TimeWatch timeWatch = new TimeWatch(TimeWatch.log);
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
        timeWatch.log("hello");
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
        timeWatch.log("world");
    }
}
