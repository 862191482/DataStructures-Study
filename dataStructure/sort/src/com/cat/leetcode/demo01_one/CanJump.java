package com.cat.leetcode.demo01_one;

public class CanJump {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 0, 4};
        int[] arr1= {2, 3, 1, 1, 4};
        System.out.println(canJump(arr1));
    }

    public static boolean canJump(int[] nums) {
        int n = nums.length;
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (i > k) return false;
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }
}
