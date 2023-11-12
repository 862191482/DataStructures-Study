package com.cat.leetcode.demo01_one;

import java.util.Arrays;

/**
 * 删除有序数组中的重复项
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] arr = {0,0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(arr));
        System.out.println(Arrays.toString(arr));
    }
    public static int removeDuplicates(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int slow = 1,fast = 1;
        while(fast < nums.length){
            if(nums[fast] != nums[fast - 1]){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
