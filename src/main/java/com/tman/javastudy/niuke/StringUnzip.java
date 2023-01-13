package com.tman.javastudy.niuke;

import java.util.Scanner;

public class StringUnzip {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            System.out.println(unZip(sc.nextLine()));
        }
    }

    public static String unZip(String str) {
        char[] input = str.toCharArray();
        if (!checkInput(input, null)) {
            return "!error";
        }
        StringBuilder res = new StringBuilder();
        int i = 0, len = input.length;
        while (i < len) {
            char c = input[i];
            if (Character.isDigit(c)) {
                StringBuilder countSb = new StringBuilder();
                countSb.append(c);
                char nextC = c;
                int nextI = i+1;
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
                i=nextI+countSb.length();
            }else {
                res.append(c);
                i++;
            }
        }
        return res.toString();
    }

    public static char findCount(char[] input, StringBuilder countSb) {
        int len = input.length;
        char c = input[0];
        if (len == 1) {
            return c;
        }
        countSb.append(c);
        char nextC = input[1];
        if (Character.isDigit(nextC)) {
            char[] remain = new char[len - 1];
            System.arraycopy(input, 1, remain, 0, len - 1);
            return findCount(remain, countSb);
        }else {
            return nextC;
        }
    }

    public static boolean checkInput(char[] input,Character lastC) {
        if (null == input || input.length == 0) {
            return false;
        }
        int len = input.length;
        char c = input[0];
        if (len == 1 && Character.isLowerCase(c)) {
            return true;
        }
        if(len == 1 && !Character.isLowerCase(c)) {
            return false;
        }
        char nextC = input[1];
        char[] remain = new char[len - 1];
        System.arraycopy(input, 1, remain, 0, len - 1);
        boolean flag=false;
        if (Character.isDigit(c)) {//当前字符是数字
            if (Character.isLowerCase(nextC)) {//下一个是小写字母
                if (null == lastC && Integer.parseInt(String.valueOf(c)) > 2) {
                    //则该数字必须大于2
                    flag = true;//第一个字符
                } else if (null != lastC && Character.isLowerCase(lastC)) {
                    if (Integer.parseInt(String.valueOf(c)) > 2 /*&& lastC != nextC*/) {//如果前一个是字母，则数字也要大于2,且前一个和下一个不能相等
                        flag = true;
                    }
                } else if (null != lastC && Character.isDigit(lastC)) {
                    flag = true;
                }
            } else if (Character.isDigit(nextC)) {//下一个是数字，合法
                flag =true;
            }
        } else if (Character.isLowerCase(c)) {// 当前字符为小写字母
            if (null == lastC) {
                flag = true;//第一个字符
            } else if (Character.isLowerCase(lastC)) {//前一个是小写字母
                if (lastC == c && lastC != nextC) {//连续不能超过两个
                    flag = true;
                } else if (c != lastC) {
                    flag = true;
                }
            } else if (Character.isDigit(lastC) && c != nextC) {//前一个是数字，则后面的字符不能相等
                flag = true;
            }
        }
        return flag && checkInput(remain,c);
    }
}
