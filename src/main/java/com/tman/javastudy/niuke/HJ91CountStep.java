package com.tman.javastudy.niuke;

import java.util.Scanner;

/**
 * @author haowanjin
 * @date 2023/12/28 9:17
 * <p>
 * HJ91 走方格的方案数
 * 请计算n*m的棋盘格子（n为横向的格子数，m为竖向的格子数）从棋盘左上角出发沿着边缘线从左上角走到右下角，
 * 总共有多少种走法，要求不能走回头路，即：只能往右和往下走，不能往左和往上走。
 * </p>
 */
public class HJ91CountStep {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
            moveCount(a, b);
        }
    }

    private static void moveCount(int m, int n) {
        int[][] dep = new int[m][n];
        dep[0][0] = 2;
        for (int i = 1; i < n; i++) {
            dep[0][i] = dep[0][i - 1] + 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    dep[i][j] = dep[i - 1][0] + 1;
                } else
                    dep[i][j] = dep[i - 1][j] + dep[i][j - 1];
            }
        }
        System.out.println(dep[m - 1][n - 1]);
    }
}
