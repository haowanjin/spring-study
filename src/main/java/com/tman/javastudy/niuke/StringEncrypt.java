package com.tman.javastudy.niuke;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * @author haowanjin
 * @date 2023/12/21 10:00
 * <p>
 *
 * </p>
 */
public class StringEncrypt {
    public static void main(String[] args) {
        char c = '0';
        int i = c + 1;

        int a = 65;

        System.out.println("a:" + (char) a);
        System.out.println((int) 'a');
        System.out.println((int) 'z');
        System.out.println((int) 'A');
        System.out.println((int) 'Z');

        System.out.println((int) c);
        System.out.println(i);
        System.out.println((char) i);
    }

    public static void encryptStr() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            String str = in.next();
            System.out.println(encrypt(str));
            System.out.println(str);
        }
    }

    public static void encrypt2(String str) {
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        char c;
        for (char ch : chars) {
            if (ch >= 'a' && ch < 'z') {
                c = (char) (ch - 'a' + 'A' + 1);
            } else if (ch >= 'A' && ch < 'Z') {
                c = (char) (ch - 'A' + 'a' + 1);
            } else if (ch >= '0' && ch < '9') {
                c = (char) (ch - '0' + 1);
            } else if (ch == 'z') {
                c = 'A';
            } else if (ch == 'Z') {
                c = 'a';
            } else if (ch == '9') {
                c = '0';
            } else {
                c = ch;
            }
            sb.append(c);
        }
        System.out.println(sb);
    }

    public static void decrypt2(String str) {
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        char c;
        for (char ch : chars) {
            if (ch > 'a' && ch <= 'z') {
                c = (char) (ch - 'a' + 'A' - 1);
            } else if (ch > 'A' && ch <= 'Z') {
                c = (char) (ch - 'A' + 'a' - 1);
            } else if (ch > '0' && ch <= '9') {
                c = (char) (ch - '0' -1);
            } else if (ch == 'a') {
                c = 'Z';
            } else if (ch == 'A') {
                c = 'z';
            } else if (ch == '0') {
                c = '9';
            } else {
                c = ch;
            }
            sb.append(c);
        }
        System.out.println(sb);
    }

    private static String encrypt(String str) {
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        for (char ch : chars) {
            if (Character.isDigit(ch)) {
                if (ch == '9') {
                    ch = '0';
                    sb.append(ch);
                } else {
                    int i = ch + 1;
                    sb.append((char) i);
                }
            } else if (ch >= 'a' && ch < 'z') {
                int i = (ch + 1) - 32;
                sb.append((char) i);
            } else if (ch >= 'A' && ch < 'Z') {
                int i = (ch + 1) + 32;
                sb.append((char) i);
            } else if (ch == 'z') {
                sb.append("A");
            } else if (ch == 'Z') {
                sb.append("a");
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static String decrypt(String str) {
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        for (char ch : chars) {
            if (Character.isDigit(ch)) {
                if (ch == '0') {
                    ch = '9';
                    sb.append(ch);
                } else {
                    int i = ch - 1;
                    sb.append((char) i);
                }
            } else if (ch > 'a' && ch <= 'z') {
                int i = (ch - 1) - 32;
                sb.append((char) i);
            } else if (ch > 'A' && ch <= 'Z') {
                int i = (ch - 1) + 32;
                sb.append((char) i);
            } else if (ch == 'a') {
                sb.append("Z");
            } else if (ch == 'A') {
                sb.append("z");
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
