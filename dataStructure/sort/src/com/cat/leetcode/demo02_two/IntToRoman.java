package com.cat.leetcode.demo02_two;

public class IntToRoman {
    public static void main(String[] args) {
        System.out.println(intToRoman(1999999));
    }
    public static String intToRoman(int num) {
        String[] symbol = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] nums = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < symbol.length; i++) {
            while(num >= nums[i]) {
                sb.append(symbol[i]);
                num -= nums[i];
            }
        }
        return sb.toString();
    }
}
