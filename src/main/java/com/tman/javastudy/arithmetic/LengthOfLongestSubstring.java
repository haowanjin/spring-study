package com.tman.javastudy.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 无重复字符的最长子串
 */
public class LengthOfLongestSubstring {
    boolean readOnly = false;
    int size = 0;
    Object[] elements = null;

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aaaaaaaaaaa"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s != null && s.length() > 0 && s != "") {
            //将传入的字符串转换成字符数组
            char[] input = s.toCharArray();
            List<String> maxStr = new ArrayList<>();
            List<String> tmpStr = new ArrayList<>();
            for (int i = 0; i < input.length; i++) {
                String cur = new String(new char[]{input[i]});
                //判断是否存在子串中
                if (tmpStr.contains(cur)) {
                    //如果长度大于当前最大子串，则覆盖
                    if (tmpStr.size() > maxStr.size()) {
                        maxStr = new ArrayList<>(tmpStr);
                    }
                    //拆分子串
                    int index = tmpStr.indexOf(cur);
                    for (int j = 0; j <= index; j++) {
                        tmpStr.remove(0);
                    }
                }
                //将当前遍历到的加入数组，形成新的子串
                tmpStr.add(cur);
            }
            if (tmpStr.size() > maxStr.size()) {
                maxStr = tmpStr;
            }
            return maxStr.size();
        }
        return 0;
    }

    public void add(Object element) {
        if (!this.readOnly) {
            int newSize = this.size + 1;
            if (newSize > this.elements.length) {
                Object[] newElements = new Object[this.elements.length + 10];
                System.arraycopy(this.elements, 0, newElements, 0, this.elements.length);
                this.elements = newElements;
            }
            this.elements[size++] = element;
        }
    }
}
