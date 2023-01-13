package com.tman.javastudy.arithmetic;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 7, 4, 5, 3, 9, 0};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        if (arr.length<2 || arr==null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr,i);//建立大根堆；
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize>0) {
            heapify(arr,0,heapSize);//调整大根堆
            swap(arr, 0, --heapSize);//将大根堆的根和最后一个元素交换，然后size缩小一个；
        }
    }

    private static void heapify(int[] arr, int index, int size) {
        //找到left
        int left = 2*index+1;
        //进行循环
        while (left<size) {
            //确定left和right中较大的位置
            int largest = left+1<size && arr[left+1]>arr[left] ? left+1 : left;
            //确定孩子和父节点中较大的位置
            largest = arr[largest]>arr[index] ? largest : index;
            //如果最大位置和父节点位置相同，则跳出循环
            if (largest == index) {
                break;
            }
            //否则，交换最大值和父节点的值，将变量更新
            swap(arr, index, largest);
            index = largest;
            left = 2*index+1;
        }
    }

    private static void heapInsert(int[] arr, int index) {//如果插入节点值要比父节点值大，
                                                            // 则交换，并且比较下一轮。
        while (arr[index]>arr[(index-1)/2]) {
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }

    private static void swap(int[] arr, int index, int i) {
        int temp = arr[i];
        arr[i] = arr[index];
        arr[index] = temp;
    }
}
