package com.tman.javastudy.niuke;

import java.util.*;

/**
 * @author haowanjin
 * @date 2024/1/3 13:57
 * <p>
 * 把m个同样的苹果放在n个同样的盘子里，允许有的盘子空着不放，问共有多少种不同的分法？
 * 注意：如果有7个苹果和3个盘子，（5，1，1）和（1，5，1）被视为是同一种分法。
 * </p>
 */
public class HJ61PushApple {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
//            System.out.println(doCount(a, b));
            dynamicProgramming(a, b);
        }

    }

    /**
     * @param m 苹果
     * @param n 盘子
     */
    private static int doCount(int m, int n) {
        if (m <= 1 || n <= 1) {
            return 1;
        }
        return doCount(m - n, n) + doCount(m, n - 1);
    }

    private static void dynamicProgramming(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i < j) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - j][j];
                }
            }
        }
        System.out.println(dp[m][n]);
    }
}
