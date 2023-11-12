package com.cat.leetcode.demo01_one;

public class Stock {
    public static void main(String[] args) {
        int[] arr = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit2(arr));
    }
//暴力法
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        int max = -Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (max < prices[j] - prices[i]) {
                    max = prices[j] - prices[i];
                }
            }
        }
        if (max <= 0) {
            return 0;
        }
        return max;
    }
//一次遍历法（一次遍历，找最小值，）
    public static int maxProfit2(int[] prices) {
        int start = 0, end = prices.length;
        if (end < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = 0;
        while (end > start) {
            if(min > prices[start]){
                min = prices[start];
            } else if (max < prices[start] - min) {
                max = prices[start] - min;
            }
            start++;
        }
        if (max <= 0) {
            return 0;
        }
        return max;
    }
}
