package com.tman.javastudy.arithmetic.kmp;

/**
 * Copyright 2010 MacroSAN, Co.Ltd. All rights reserved.
 * 文件名称  ：ViolenceMatch
 * 描述    ：
 * 作者    ：haowanjin
 * 创建时间：2019/9/9 8:32
 * *******************************************************
 * 修改时间        修改人       修改原因
 * ********************************************************
 */

public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "美美美美美女人美了才完美";
        String s2 = "美女人美了";
        System.out.println(violenceMatch(s1,s2));
    }

    public static int violenceMatch(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int i = 0, j = 0;
        while (i < str1.length && j < str2.length) {
            if (str1[i] == str2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        return j == str2.length ? (i - j) : -1;
    }
}
