package com.tman.javastudy.arithmetic;

import com.tman.javastudy.bean.FruitEnum;

public class Queen8 {
    private int maxSize = 8;
    private static int count = 0;
    private int[] array = new int[maxSize];

    public static void main(String[] args) {
        /*Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println("打印次数为:::"+count);*/
        String a = "Allow";
        String d = "Deny";
        System.out.println();
        FruitEnum.APPLE.getColor();
    }

    private void check(int n) {
        if (n == maxSize) {
            print();
            return;
        }
        for (int i = 0; i < maxSize; i++) {
            array[n]=i;
            if (judge(n)) {
                check(n+1);
            }
        }
    }

    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] ||(Math.abs(i-n))==Math.abs(array[i]-array[n])) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        for (int i = 0; i < maxSize; i++) {
            System.out.print(array[i]+" ");
        }
        count++;
        System.out.println();
    }
}
