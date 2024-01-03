package com.tman.javastudy.niuke;

import java.util.Scanner;

/**
 * @author haowanjin
 * @date 2024/1/3 15:15
 * <p>
 *
 * </p>
 */
public class HJ52StringDistance {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s1 = in.nextLine();
            String s2 = in.nextLine();
            doCalculate(s1, s2);
        }
    }

    private static void doCalculate(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        System.out.println(dp[s1.length()][s2.length()]);
    }

    private static void doCalculateCompress(String s1, String s2) {
        int[][] dp = new int[2][s2.length() + 1];
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    if (j == 1) {
                        dp[i % 2][j] = i - 1;
                    } else {
                        dp[i % 2][j] = dp[(i + 1) % 2][j - 1];
                    }
                } else {
                    if (j == 1) {
                        dp[i % 2][j] = dp[(i + 1) % 2][j - 1];
                    } else {
                        dp[i % 2][j] = Math.min(dp[(i + 1) % 2][j] + 1, Math.min(dp[(i + 1) % 2][j - 1] + 1, dp[i % 2][j - 1] + 1));
                    }
                }
            }
        }
        System.out.println(dp[s1.length()%2][s2.length()]);
    }
}
