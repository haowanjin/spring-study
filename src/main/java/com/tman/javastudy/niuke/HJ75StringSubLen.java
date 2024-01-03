package com.tman.javastudy.niuke;

import java.util.Scanner;

/**
 * @author haowanjin
 * @date 2024/1/3 17:41
 * <p>
 * 给定两个只包含小写字母的字符串，计算两个字符串的最大公共子串的长度。
 * </p>
 */
public class HJ75StringSubLen {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str1 = in.nextLine();
            String str2 = in.nextLine();
            countSubStrLen(str1, str2);
        }
    }

    private static void countSubStrLenByDp(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        int res = 0;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        System.out.println(res);

    }

    /**
     * @param str1
     * @param str2
     */
    private static void countSubStrLen(String str1, String str2) {
        String s1 = str1.length() > str2.length() ? str1 : str2;//较长的字符串
        String s2 = str1.length() > str2.length() ? str2 : str1;//较短的字符串
        int n = 0;
        for (int i = 0; i < s2.length(); i++) {
            for (int j = s2.length(); j > i; j--) {
                if (s1.contains(s2.substring(i, j))) {
                    n = Math.max(j - i, n);
                    break;
                }
            }
        }
    }
}
