/**
 * Copyright 2010 MacroSAN, Co.Ltd. All rights reserved.
 * 文件名称  ：BubbleSort
 * 描述    ：
 * 作者    ：haowanjin
 * 创建时间：2019/9/23 16:07
 * *******************************************************
 * 修改时间        修改人       修改原因
 * ********************************************************
 */
package com.tman.javastudy.arithmetic;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 7, 4, 5, 3, 9, 0};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }

    }

    private static void swap(int[] arr, int i, int i1) {
        int tmp = arr[i];
        arr[i] = arr[i1];
        arr[i1]=tmp;
    }
}
