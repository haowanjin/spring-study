package com.tman.javastudy.arithmetic;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 7, 4, 5, 3, 9, 0};
        int[] arr1 = {3, 2, 1, 3, 2, 5, 4};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        quickSort3(arr2, 0, arr1.length - 1);
        // quickSort(arr1, 0, arr1.length - 1);
        System.out.println(Arrays.toString(arr2));
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

    /**
     * 选择一个基数，将数组中小于基数的数放到基数的左边，大于基数的数放到基数的右边
     *
     * @param arr 待排序数组
     * @param L   左边界
     * @param R   有边界
     * @return 小于基数的左边界，大于基数的左边界
     */
    public static int[] partition(int[] arr, int L, int R) {
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
        return new int[]{less, more};
    }

    public static int partition2(int[] arr, int l, int r) {
        int target = arr[l];
        int index = l;
        while (l < r) {
            while (l < r) {
                if (arr[r] < target) {
                    swap(arr, index, r);
                    index = r;
                    l++;
                    break;
                }
                r--;
            }
            while (l < r) {
                if (arr[l] > target) {
                    swap(arr, index, l);
                    index = l;
                    r--;
                    break;
                }
                l++;
            }
        }
//        arr[index] = target;
        return index;
    }

    public static void quickSort3(int[] arr, int l, int r) {
        if (arr == null || arr.length < 2 || l >= r) {
            return;
        }
        int p = partition2(arr, l, r);
        quickSort3(arr, l, p - 1);
        quickSort3(arr, p + 1, r);
    }

    public static void quickSort2(int[] arr, int l, int r) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (l < r) {
            int[] ints = partition(arr, l, r);
            quickSort2(arr, l, ints[0]);
            quickSort2(arr, ints[1], r);
        }
    }
}
