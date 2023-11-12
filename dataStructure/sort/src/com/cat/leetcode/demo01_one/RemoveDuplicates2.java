package com.cat.leetcode.demo01_one;

import java.util.Arrays;

public class RemoveDuplicates2 {
    public static void main(String[] args) {
        int[] arr3 = {0,0,0,1,1,2,3,3,3,3,4};
        int[] arr2 = {0,0,0,1,1,2,3,3,3,3,4};
        System.out.println(removeDuplicates(arr3));
        System.out.println(Arrays.toString(arr3));
    }
    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        int slow = 2,fast = 2;
        if(n <= 2){
            return n;
        }
        while(fast < n){
            if(nums[fast] != nums[slow - 2]){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
