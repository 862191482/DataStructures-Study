package com.cat.leetcode.demo01_one;

public class CanJump_Min {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 0, 4};
        int[] arr1= {2, 3, 1, 1, 4};
        System.out.println(jump3(arr1));
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
    public static int jump1(int[] nums) {
        if(!canJump(nums)){
            return -1;
        }
        int ans = 0;
        int start = 0;
        int end = 1;
        while (end < nums.length)
        {
            int maxPos = 0;
            for (int i = start; i < end; i++)
            {
                // 能跳到最远的距离
                maxPos = Math.max(maxPos, i + nums[i]);
            }
            start = end;      // 下一次起跳点范围开始的格子
            end = maxPos + 1; // 下一次起跳点范围结束的格子
            ans++;            // 跳跃次数
        }
        return ans;
    }
//贪心算法(反向查找)
    public static int jump2(int[] nums) {
        if(!canJump(nums)){
            return -1;
        }
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
    //贪心算法(正向查找)
    public static int jump3(int[] nums) {
        if(!canJump(nums)){
            return -1;
        }
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
