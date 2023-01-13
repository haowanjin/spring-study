package com.tman.javastudy.arithmetic;

import java.util.Scanner;

public class ConvertBinary {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            System.out.println(toBinary(n).length());
        }
    }

    public static String toBinary(int input) {
        StringBuilder sb = new StringBuilder();
        while (input != 1) {
            int res = input % 2;
            if (res == 1)
                sb.append(res);
            input = input / 2;
        }
        sb.append(1);
        sb.reverse();
        return sb.toString();
    }
}

