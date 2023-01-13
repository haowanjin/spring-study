package com.tman.javastudy.arithmetic;

import java.util.Arrays;

public class InserSort {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 7, 4, 5, 3, 9, 0};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
        public static void insertSort ( int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        //从第二个位置开始，代表当前要被排的数
        for (int i = 1; i < arr.length; i++) {
            //与前一个数比较，如果前一个数的位置大于等于零，且前一个数大于当前数，则交换位置，继续向前看
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }
        public static void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
