package com.tman.javastudy.arithmetic;

import java.util.Stack;

/**
 * |单调栈
 */
public class SingleStack {
    public static void main(String[] args) {
        int[] arr = {5,4,4,4,3,3,3,5};
        System.out.println(calculations(arr));
    }

    public static int nextIndex(int size, int index) {
        return index == size-1 ? 0 : index + 1;
    }

    public static int getCollections(int n) {
        return n == 1L ? 0 : n * (n - 1) / 2;
    }

    public static int calculations(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        int size = arr.length;
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        int max = arr[maxIndex];
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(max));
        int index = nextIndex(size, maxIndex);
        while (index != maxIndex) {
            while (!stack.isEmpty() && stack.peek().value < arr[index]) {
                int times = stack.pop().times;
                res += getCollections(times) + times * 2;
            }
            if (!stack.isEmpty() && stack.peek().value == arr[index]) {
                stack.peek().times += 1;
            } else {
                stack.push(new Pair(arr[index]));
            }
            index = nextIndex(size, index);
        }
        while (!stack.isEmpty()) {
            int times = stack.pop().times;
            res += getCollections(times);
            if (!stack.isEmpty()) {
                res += times;
                if (stack.size() > 1) {
                    res += times;
                } else {
                    res += times == 1 ? 0 : times;
                }
            }
        }
        return res;
    }
}

class Pair {
    public int value;
    public int times;

    public Pair(int value) {
        this.value = value;
        this.times = 1;
    }
}
