package com.cat.leetcode.demo01_one;

import java.util.Arrays;

public class RotatingArray {
    public static void main(String[] args) {
       int[] nums = {1,2,3,4,5,6,7};
        rotate2(nums,3);
        System.out.println(Arrays.toString(nums));
        reverse(nums,0, nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }
    //反转
    public static void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }
}
