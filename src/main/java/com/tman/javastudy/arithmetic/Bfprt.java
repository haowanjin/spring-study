package com.tman.javastudy.arithmetic;

import java.util.Arrays;

public class Bfprt {

    static int[] a = new int[2];

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 7, 4, 5, 3, 9, 0};
        System.out.println(bfprt(arr,0,arr.length-1,5));
    }

    public static int getMedian(int[] root, int begin, int end) {
        Arrays.sort(root);
        int mid = begin + (end - begin) / 2;
        return root[mid];
    }

    public static int medianOfMedians(int[] root, int star, int finish) {
        int num = finish - star + 1;//求出长度
        int offset = num % 5 == 0 ? 0 : 1;//最后如果剩余的数不足5个，我们也将其分成一个小组，和前面同等对待
        int range = num / 5 + offset;
        int[] median = new int[range];//这个数组存的是每个小组内的中位数
        for (int i = 0; i < range; i++)//依次往median数组中填数
        {
            int beginI = star + i * 5;//第i组数对应root数组上的位置
            int endI = beginI + 4;
            median[i] = getMedian(root, beginI, Math.min(endI, finish));
        }
        return bfprt(median, 0, range - 1, range / 2);//求出生成好的median数组的中位数，作为partation函数的划分值
    }

    public static int bfprt(int root[], int begin, int end, int k) {
        if (begin == end)//数组中只有一个数时，直接返回
            return root[begin];
        int divide = medianOfMedians(root, begin, end);//求出以哪个数作为划分值
        partation(root, begin, end, divide);//注意，进行完partation过程后，root数组已经不是无序的了
        if (k >= a[0] && k <= a[1])//如果需要求的数正好在等于区域，直接返回root[k]
            return root[k];
        else if (k < a[0])//此时我们要找的数比divide小，递归求前半部分
            return bfprt(root, begin, a[0] - 1, k);
        else //此时我们要找的数比divide大，递归求后半部分
            return bfprt(root, a[1] + 1, end, k);
    }

    static void partation(int root[], int beginJ, int endJ, int number)//partation函数求的是等于number的范围
    {
        int less = beginJ - 1;
        int more = endJ + 1;
        int cur = beginJ;
        while (cur < more) {
            if (root[cur] < number) {
                less++;
                swap(root, cur, less);
                cur++;
            } else if (root[cur] == number)
                cur++;
            else {
                more--;
                swap(root, cur, more);
            }
        }
        a[0] = less + 1;
        a[1] = more - 1;
    }

    static void swap(int root[], int a, int b) {
        int temp = root[a];
        root[a] = root[b];
        root[b] = temp;
    }
}
