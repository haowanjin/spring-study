package com.tman.javastudy.niuke;

public class ExampleTest {
    public static void main(String[] args) {
        System.out.println(unzip("a3b14d"));
    }

    public static String unzip(String str) {
        StringBuilder sb = new StringBuilder();
        if (str == null || str.equals(" ")) {
            return "!error";
        }
        char[] chars = str.toCharArray();
        StringBuilder builder=new StringBuilder();
        for (int i = 0; i < chars.length;i++) {
            while (isNumeric(chars[i])) {
                builder.append(chars[i]);
                continue;
            }
            if(builder.toString().equals("\\s")){
                for (int j = 0; j < Integer.parseInt(builder.toString())-1; j++) {
                    sb.append(chars[i + 1]);
                }
                builder =builder.delete(0,builder.length());
            }

            if(isChar(chars[i])){
                sb.append(chars[i]);
            } else return "!error";
        }
            return sb.toString();
    }

    public static boolean isNumeric(char c) {
        if (!Character.isDigit(c)) {
            return false;
        }
        return true;
    }

    public static boolean isChar(char c) {
        if (c >= 'a' && 'z' >= c) {
            return true;
        }
        return false;
    }
}
