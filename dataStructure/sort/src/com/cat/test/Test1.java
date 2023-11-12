package com.cat.test;

public class Test1 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[(i + k) % n] = nums[i];
        }
        System.arraycopy(arr,0,nums,0,n);
    }
    //翻转
    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums,0, nums.length);
        reverse(nums,0,k - 1);
        reverse(nums, k, nums.length);
    }
    public static void reverse(int[] nums, int start, int end) {
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
