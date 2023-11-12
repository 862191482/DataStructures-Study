package com.cat.leetcode.demo02_two;

import java.util.Arrays;

public class H_Index {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        int[] arr2 = {100};
        System.out.println(hIndex(arr));
    }

    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }
}
