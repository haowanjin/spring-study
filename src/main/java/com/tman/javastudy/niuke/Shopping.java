package com.tman.javastudy.niuke;

import java.util.*;

public class Shopping {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(22, 5);
        map.put(33, 5);
        map.put(1, 5);
        map.put(26, 5);
        map.put(4, 5);
        Set<Integer> integers = map.keySet();
        System.out.println(integers);
        /*Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Map<Integer, Integer> map = new HashMap<>();
            int n = Integer.parseInt(in.nextLine());
            for (int i = 0; i < n; i++) {
                String str = in.nextLine();
                String[] data = str.split(" ");
                int k = Integer.parseInt(data[0]);
                int v = Integer.parseInt(data[1]);
                if (map.containsKey(k)) {
                    map.put(k, map.get(k) + v);
                } else {
                    map.put(k, v);
                }
            }
            Set<Integer> integers = map.keySet();
            System.out.println(integers);
            Iterator<Integer> keys = integers.iterator();
            while (keys.hasNext()) {
                int key = keys.next();
                System.out.print(key+"\t");
                System.out.println(key + " " + map.get(key));
            }
        }*/
    }

    public static void shopping() {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main2.class.getClassLoader().getResourceAsStream("data2.txt"));
        while (scanner.hasNext()) {
            // 总的钱数
            int total = scanner.nextInt();
            // 希望购买物品的个数
            int num = scanner.nextInt();
            // 每个物品的价格
            int[] price = new int[num + 1];
            // 每个物品的权重
            int[] value = new int[num + 1];
            // 是主件还是附件
            int[] check = new int[num + 1];

            // 每一个数赋0
            price[0] = 0;
            value[0] = 0;
            check[0] = 0;

            // 读取输入数据
            for (int i = 1; i <= num; i++) {
                price[i] = scanner.nextInt();
                value[i] = scanner.nextInt();
                check[i] = scanner.nextInt();
            }

            // 结果数组
            // 行代表物品个数，列代表钱数
            int[][] result = new int[num + 1][total + 1];
            // 第一列赋0
            for (int j = 0; j <= num; j++) {
                result[j][0] = 0;
            }

            for (int i = 1; i <= total; i++) {
                for (int j = 1; j <= num; j++) {

                    // 如果是附件
                    if (check[j] > 0) {

                        // result[j-1][i-price[j]] 表示使用i-price[j]的总钱数，最多买j-1个物品的最大值

                        // 总的钱数比(当前物品+他的主件)所需要的钱多
                        if (i > price[j] + price[check[j]]) {
                            int w = result[j - 1][i - price[j]] + value[j] * price[j];
                            result[j][i] = Math.max(w, result[j - 1][i]);
                        }

                    } else {
                        // 总钱数可以买主件
                        if (i >= price[j]) {
                            int w = result[j - 1][i - price[j]] + value[j] * price[j];
                            result[j][i] = Math.max(w, result[j - 1][i]);
                        }
                    }

                }
            }
            System.out.println(result[num][total]);
        }
        scanner.close();
    }
}

class Product {
    int id;
    int v;
    int p;
    int q;
    boolean buy = false;

}