package com.cat.leetcode.demo02_two;

public class LastString {
    public static void main(String[] args) {
        String s = "luffy is still joyboy";
        System.out.println(lengthOfLastWord(s));
    }
    public static int lengthOfLastWord(String s) {
        if(s == ""){
            return -1;
        }
        String[] split = s.trim().split(" ");
        return split[split.length - 1].length();
    }
}
