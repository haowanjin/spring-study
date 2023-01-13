package com.tman.javastudy.arithmetic;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 7, 4, 5, 3, 9, 0};
        int[] arr1 = {3,2,1,3,2,5,4};
        quickSort2(arr, 0, arr.length - 1);
       // quickSort(arr1, 0, arr1.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        int tmp;
        int l = left;
        int r = right;
        int index = arr[mid];
        while (l < r) {
            while (l < r && arr[r] > index) {
                r--;
            }
            while (l < r && arr[l] <= index) {
                l++;
            }

            if (l < r) {
                tmp = arr[l];
                arr[l] = arr[r];
                arr[r] = tmp;
            }
        }
        arr[mid] = arr[l];
        arr[l] = index;
        quickSort(arr, left, l - 1);
        quickSort(arr, l + 1, right);
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[]  partition(int[] arr, int L, int R) {
        int less = L - 1;
        int cur = arr[R];
        int more = R;
        while (L < more) {
            if (arr[L] < cur) {
                swap(arr, ++less, L++);
            } else if (arr[L] > cur) {
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[]{less+1, more};
    }
    public static void quickSort2(int[] arr, int L, int R) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (L < R) {
            int[] ints = partition(arr, L, R);
            quickSort2(arr, L, ints[0] - 1);
            quickSort2(arr, ints[1], R);
        }
    }
}
