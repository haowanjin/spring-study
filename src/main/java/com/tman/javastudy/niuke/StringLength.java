package com.tman.javastudy.niuke;

import java.util.Scanner;

public class StringLength {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        while (true) {
            if (s.equals("exit")) {
                return;
            }
            String[] ss = s.split(" ");
            int len = ss.length;
            System.out.println(ss[len-1].length());
        }

    }

    public static String reversInt(int n) {
        StringBuilder builder = new StringBuilder(n);
        builder.reverse();
        return builder.toString();
    }
}
