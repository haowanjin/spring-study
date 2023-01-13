package com.tman.javastudy.arithmetic.kmp;

import java.util.Arrays;

public class KMPMatch {
    public static void main(String[] args) {
//        String s1 = "BBC ABCDAB ABCDABCDABDE";
//        String s2 = "ABCDABD";
        String s1 = "ABACDDCDDDCDDAAADCDCD";
        String s2 = "AABCDAABDC";
        int minValue = Integer.MIN_VALUE;

        System.out.println("index = " + getIndexOf(s1, s2));
        System.out.println("index2 = " + getIndexOf2(s1, s2));
    }

    public static int getIndexOf(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        Arrays.sort(s1);

        int[] nextArr = getNextArr(s2);
        int h1 = 0, h2 = 0;
        while (h1 < s1.length && h2 < s2.length) {
            if (s1[h1] == s2[h2]) {
                h1++;
                h2++;
            } else if (nextArr[h2] == -1) {
                h1++;
            } else {
                h2 = nextArr[h2];
            }
        }
        return h2 == s2.length ? h1 - h2 : -1;
    }

    public static int[] getNextArr(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < str2.length) {
            if (str2[i - 1] == str2[cn]) {
                next[i++] = ++cn;
            } else if (next[cn] > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static int getIndexOf2(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int[] next = kmpNext(str2);
        for (int i = 0, j = 0; i < s1.length; i++) {
            while (j > 0 && s1[i] != s2[j]) {
                j = next[j - 1];
            }
            if (s1[i] == s2[j]) {
                j++;
            }
            if (j == s2.length) {
                return i - j + 1;
            }
        }
        return -1;
    }
}
