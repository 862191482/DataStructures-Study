package com.cat.leetcode.demo02_two;

import java.util.*;

public class RandomizedSet {

    List<Integer> nums;
    Map<Integer,Integer> indices;
    Random random;
    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        indices = new HashMap<Integer,Integer>();
        random = new Random();
    }

    public boolean insert(int val) {
        return true;
    }

    public boolean remove(int val) {
        return true;
    }

    public int getRandom() {
        return 0;
    }
}
