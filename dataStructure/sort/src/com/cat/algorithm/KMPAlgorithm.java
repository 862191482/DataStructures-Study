package com.cat.algorithm;

import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext("AAAB");
        System.out.println("next="+ Arrays.toString(next));
    }
    //写出我们的KMP搜索算法
    /**
     *
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表，是子串对应的部分匹配表
     * @return 如果是-1就是没有匹配到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next){
        //遍历str1
        for (int i = 0,j = 0; i < str1.length(); i++) {
            //处理str1.charAt(i) != str2.charAt(j)
            //KMP算法的核心点
            while (j >0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j == str2.length()){
                return i - j + 1;
            }
        }
        return -1;
    }
    //获取到一个字符串（子串）的部分匹配值表
    public static int[] kmpNext(String dest){
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//字符串的长度为1，部分匹配值就是0
        for (int i = 1,j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j) 时,需要从next[j-1]获取新的j
            //直到dest.charAt(i) == dest.charAt(j)成立时才退出
            //这是KMP算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }
            //当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是1
            if(dest.charAt(i) == dest.charAt(j)){//charAt返回指定索引处的字符
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
