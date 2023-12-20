package com.tman.javastudy.niuke;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author haowanjin
 * @date 2023/12/20 11:22
 * <p>
 *
 * </p>
 */
public class AsdwMove {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            int[] arr = move(s);
            System.out.println(arr[0] + "," + arr[1]);
        }
    }

    private static int[] move(String s) {
        List<Character> list = Arrays.asList('A', 'S', 'D', 'W');
        StringBuilder sb = new StringBuilder();
        int l = 0, r = 0;
        if (s != null && s.length() > 0) {
            String[] split = s.split(";");
            retry:
            for (String s1 : split) {
                char[] chars = s1.toCharArray();
                if (chars.length <1) {
                    continue ;
                }
                char m = chars[0];
                if ( !list.contains(m)) {
                    continue;
                }
                for (int i = 1; i < chars.length; i++) {
                    if (!Character.isDigit(chars[i])) {
                        sb.delete(0, sb.length());
                        continue retry;
                    }
                    sb.append(chars[i]);
                }
                if (sb.length() > 0) {
                    int digit = Integer.parseInt(sb.toString());
                    if (m == 'A') {
                        l=l-digit;
                    }
                    if (m == 'D') {
                        l=l+digit;
                    }
                    if (m == 'S') {
                        r-=digit;
                    }
                    if (m == 'W') {
                        r+=digit;
                    }
                    sb.delete(0, sb.length());
                }
            }
        }
        return new int[]{l, r};
    }
}
