package com.alan.java.debug.sample.content;

import java.util.Arrays;

/**
 * 热部署
 * CTRL + SHIFT + F9
 */
public class HotDeployTest {

    private static int num = 0;
    private static int x = 10;

    private static int test1(int n1, int n2) {
        if (n2 == 0) {
            return 0;
        }
        return n1/n2;
    }

    private static int[] test2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] - 1;
        }
        return nums;
    }

    private static int test3() {
        num++;
        return num / 2;
    }

    private static int test4() {
        return num + x;
    }

    public static void main(String[] args) {
        System.out.println(test1(2, 0));
        System.out.println(Arrays.toString(test2(new int[] {1, 2})));
        System.out.println(test3());
        System.out.println(test4());
    }


}
