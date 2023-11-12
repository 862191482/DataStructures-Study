package com.cat.leetcode.demo01_one;

public class RemoveElement {
    public static void main(String[] args) {
        int[] arr = {1,2,3,2,5,2,7,8,9};
        int val = 2;
        System.out.println(removeElement2(arr, 2));
    }
    public static int removeElement(int[] nums, int val) {
        int left = 0;
        int n = nums.length;
        for (int right = 0; right < n; right++) {
            if(nums[right] != val){
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }
    public static int removeElement2(int[] nums, int val) {
        int left = 0,right = nums.length;
        while(left < right){
            if(nums[left] == val){
                nums[left] = nums[right - 1];
                right--;
            }else{
                left++;
            }
        }
        return right;
    }
}
