package com.tman.javastudy.arithmetic;

import java.util.Scanner;

/**
 * 简易压缩字符串
 */
public class StringUnZip {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String input = in.nextLine();
            System.out.println(upZip(input));
        }
    }

    public static String upZip(String str) {
        char[] input = str.trim().toCharArray();
        if (!checkInput(input, null)) {
            return "!error";
        }
        int len = input.length, i = 0;
        StringBuilder res = new StringBuilder();
        while (i < len) {
            char c = input[i];
            if (Character.isDigit(c)) {
                StringBuilder countSb = new StringBuilder();
                countSb.append(c);
                char nextC = c;
                int nextI = i + 1;
                if (nextI < len && Character.isDigit(input[nextI])) {
                    char[] remain = new char[len - nextI];
                    System.arraycopy(input, nextI, remain, 0, len - nextI);
                    nextC = findCount(remain, countSb);
                } else if (nextI < len) {
                    nextC = input[nextI];
                }
                int count = Integer.parseInt(countSb.toString());
                for (int j = 0; j < count; j++) {
                    res.append(nextC);
                }
                i = nextI + countSb.length();
            } else {
                res.append(c);
                i++;
            }
        }
        return res.toString();
    }

    public static char findCount(char[] input, StringBuilder sb) {
        int len = input.length;
        char c = input[0];
        if (len == 1) {
            return c;
        }
        sb.append(c);
        char nextC = input[1];
        if (Character.isDigit(nextC)) {
            char[] remain = new char[len - 1];
            System.arraycopy(input, 1, remain, 0, len - 1);
            return findCount(remain, sb);
        } else {
            return nextC;
        }
    }

    public static boolean checkInput(char[] input, Character lastC) {
        if (input == null || input.length == 0) {
            return false;
        }
        int len = input.length;
        char c = input[0];
        if (len == 1 && Character.isLowerCase(c)) {
            return true;
        }
        if (len == 1 && !Character.isLowerCase(c)) {
            return false;
        }
        char nextC = input[1];
        char[] remain = new char[len - 1];
        System.arraycopy(input, 1, remain, 0, len - 1);
        boolean flag = false;
        if (Character.isDigit(c)) {
            if (Character.isLowerCase(nextC)) {
                if (null == lastC && Integer.parseInt(String.valueOf(c)) > 2) {
                    flag = true;
                } else if (null != lastC && Character.isLowerCase(lastC)) {
                    if (Integer.parseInt(String.valueOf(c)) > 2 && lastC != nextC) {
                        flag = true;
                    }
                } else if (null != lastC && Character.isDigit(lastC)) {
                    flag = true;
                }
            } else if (Character.isDigit(nextC)) {
                flag = true;
            }
        } else if (Character.isLowerCase(c)) {
            if (null == lastC) {
                flag = true;
            } else if (null != lastC && Character.isLowerCase(lastC)) {
                if (lastC == c && c != nextC) {
                    flag = true;
                } else if (c != lastC) {
                    flag = true;
                }
            } else if (Character.isDigit(lastC) && c != nextC) {
                flag = true;
            }
        }
        return flag && checkInput(remain, c);
    }

}
