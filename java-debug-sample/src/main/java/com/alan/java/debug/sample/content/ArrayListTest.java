package com.alan.java.debug.sample.content;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(7);
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);

        System.out.println(list);

        System.out.println(list.get(2));

        int index = 2;
        list.remove(index);

        System.out.println(list);
    }
}
