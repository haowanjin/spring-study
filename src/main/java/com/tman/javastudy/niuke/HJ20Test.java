package com.tman.javastudy.niuke;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author haowanjin
 * @date 2023/12/21 11:01
 * <p>
 *
 * </p>
 */
public class HJ20Test {
    public static void main(String[] args) {
        char[] cc = {'a', 'b', 'C', 'D'};
        Arrays.sort(cc);
        System.out.println(cc);
        calc("Eqr v9oEb12U2ur4xu7rd931G1f50qDo");
    }

    public static void test() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            calc(str);
        }
    }

    private static void calc(String str) {
        str = str.replace(" ", "");
        char[] chars = str.toCharArray();
        int len = chars.length / 2;
        char[] ou = new char[chars.length % 2 == 0 ? len : len + 1];
        char[] ji = new char[len];
        int o = 0, j = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i % 2 == 0) {
                ou[o++] = chars[i];
            } else {
                ji[j++] = chars[i];
            }
        }
        Arrays.sort(ou);
        Arrays.sort(ji);
        o = 0;
        j = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i % 2 == 0) {
                chars[i] = ou[o++];
            } else {
                chars[i] = ji[j++];
            }
        }
        convertStr(chars);
    }

    private static void convertStr(char[] chars) {
        /*  对以上需要进行转换的字符所代表的十六进制用二进制表示并倒序，然后再转换成对应的十六进制大写字符（注：字符 a~f 的十六进制对应十进制的10~15，大写同理）。
            如字符 '4'，其二进制为 0100 ，则翻转后为 0010 ，也就是 2 。转换后的字符为 '2'。
            如字符 ‘7’，其二进制为 0111 ，则翻转后为 1110 ，对应的十进制是14，转换为十六进制的大写字母为 'E'。
            如字符 'C'，代表的十进制是 12 ，其二进制为 1100 ，则翻转后为 0011，也就是3。转换后的字符是
         */
        Map<Character, Integer> dict = new HashMap<Character, Integer>() {{
            put('0', 0);
            put('1', 1);
            put('2', 2);
            put('3', 3);
            put('4', 4);
            put('5', 5);
            put('6', 6);
            put('7', 7);
            put('8', 8);
            put('9', 9);
            put('A', 10);
            put('B', 11);
            put('C', 12);
            put('D', 13);
            put('E', 14);
            put('F', 15);
            put('a', 10);
            put('b', 11);
            put('c', 12);
            put('d', 13);
            put('e', 14);
            put('f', 15);
        }};
        Map<Integer, String> dict2 = new HashMap<Integer, String>() {{
            put(0, "0");
            put(1, "1");
            put(2, "2");
            put(3, "3");
            put(4, "4");
            put(5, "5");
            put(6, "6");
            put(7, "7");
            put(8, "8");
            put(9, "9");
            put(10, "A");
            put(11, "B");
            put(12, "C");
            put(13, "D");
            put(14, "E");
            put(15, "F");

        }};
        StringBuilder sb1 = new StringBuilder();
        for (char ch : chars) {
            Integer integer = dict.get(ch);
            if (integer == null) {
                sb1.append(ch);
                continue;
            }
            String s = Integer.toBinaryString(integer);
            while (s.length() < 4) {
                s = "0" + s;
            }
            StringBuilder sb = new StringBuilder(s);
            s = sb.reverse().toString();
            int i = Integer.parseInt(s, 2);
            sb1.append(dict2.get(i));
        }
        System.out.println(sb1);
    }
}
