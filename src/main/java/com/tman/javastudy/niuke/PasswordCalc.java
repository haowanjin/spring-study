package com.tman.javastudy.niuke;

import java.util.Scanner;

/**
 * @author haowanjin
 * @date 2023/12/21 13:32
 * <p>
 *
 * </p>
 */
public class PasswordCalc {
    public static void main(String[] args) {
        calcPwd();
    }

    public static void calcPwd() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            doCalc(str);
        }
    }

    private static void doCalc(String str) {
        int len = str.length();
        // 状态：对比的两个字符索引起始和终止索引位置
        // 定义: 字符串s的i到j字符组成的子串是否为回文子串
        boolean[][] dp = new boolean[len][len];
        int res = 0;
        // base case
        for (int i = 0; i < len - 1; i++) {
            dp[i][i] = true;
        }

        for (int r = 1; r < len; r++) {
            for (int l = 0; l < r; l++) {
                // 状态转移：如果左右两字符相等,同时[l+1...r-1]范围内的字符是回文子串
                // 则 [l...r] 也是回文子串
                if (str.charAt(l) == str.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    // 不断更新最大长度
                    res = Math.max(res, r - l + 1);
                }
            }
        }
        System.out.println(res);
    }

}
