package com.tman.javastudy.niuke;

public class GetMaxSubString {
    public static void main(String[] args) {
        String input ="ssssssbbbbbbdssdsssssssssssbbb";
        System.out.println(getMaxSubString(input));
    }

    public static String getMaxSubString(String input) {
        int start=0,end=1,maxStart=0,maxEnd=1;
        char[] chars = input.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            //判断如果不相等，则计算器长度
            if(chars[i-1]!=chars[i]) {//设置其重复子串结束位置
                end=i;
                //子串长度
                int len = end - start;
                //子串长度大于现有的最大子串长度
                if (len > (maxEnd - maxStart)) {
                    //则进行赋值
                    maxStart = start;
                    maxEnd = end;
                }
                //开始截取位置为end结束位置
                start = end;
            }
        }
        //截取字符串
        return input.substring(maxStart,maxEnd);
    }
}
