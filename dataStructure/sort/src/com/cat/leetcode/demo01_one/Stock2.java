package com.cat.leetcode.demo01_one;

public class Stock2 {
    public static void main(String[] args) {
        int[] arr = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(arr));
    }
    //贪心算法
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 1; i < len; ++i) {
            int diff = prices[i] - prices[i - 1];
            sum += (diff > 0 ? diff : 0);
        }
        return sum;
    }

}
