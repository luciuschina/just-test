package com.haozhuo.lucius.justtest.java.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lucius on 8/6/18.
 */
public class Test {

    public static void main(String[] args) {
        testUnmodifiableList();
    }

    /**
     * Collections中还有unmodifiableCollection(),unmodifiableSet(),unmodifiableMap()等方法
     */
    public static void testUnmodifiableList() {
        List<String> immutableList = Collections.unmodifiableList(Arrays.asList("11", "212"));
        System.out.println(immutableList.get(0)); // 11
        immutableList.add("1"); // Exception in thread "main" java.lang.UnsupportedOperationException

    }
}
