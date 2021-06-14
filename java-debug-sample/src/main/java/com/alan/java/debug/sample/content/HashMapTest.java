package com.alan.java.debug.sample.content;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

/**
 * HashMap为什么不是线程安全的？
 * 1. 当table==null没有元素的时候，插入两个元素，会同时创建两个table，分别在这两个table中插入元素，但最后只有一个table赋值给了HashMap本身，这就出现线程不安全的行为
 * 2. 当table[i]这个下标有元素的时候，同时在i位置再插入两个元素，会导致其中一个可能被挤掉
 * 3. 当table[i]这个下标没有元素的时候，同时在i位置插入两个元素，会导致其中一个可能被挤掉
 * 4. 当元素的数量已经达到了扩容门槛的时候，再同时插入两个元素，有可能导致扩容两次
 * 5. 当元素的数量已经达到了扩容门槛的时候，再同时插入两个元素，有可能导致整个Map被清空
 *
 */
public class HashMapTest {
    public static void main(String[] args) throws InterruptedException {
        HashMap<Integer, String> map = new HashMap<>(8);

        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
        map.put(5, "5");
        map.put(6, "6");
        map.put(17, "17");

        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            map.put(1, "1");
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            map.put(9, "9");
            countDownLatch.countDown();
        }).start();

        countDownLatch.await();

        System.out.println(map);
    }
}
