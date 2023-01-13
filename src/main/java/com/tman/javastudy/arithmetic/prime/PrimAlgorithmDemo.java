package com.tman.javastudy.arithmetic.prime;

import java.util.Arrays;

public class PrimAlgorithmDemo {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        MGraph graph = new MGraph(vertex, data, weight);
       /* graph.showGraph();
        prim(graph,0);*/
       hanoiTower(4,'A','B','C');
    }

    /**
     * @param sum
     * @param a
     * @param b
     * @param c
     * @return void
     * @description
     * @author haowanjin
     * @date 2019/9/3 8:40
     */
    public static void hanoiTower(int sum, char a, char b, char c) {
        if (sum == 1) {
            System.out.println(sum+"号盘子从"+a+"-->"+c);
        } else {
            hanoiTower(sum - 1, a, c, b);
            System.out.println(sum+"号盘子从"+a+"-->"+c);
            hanoiTower(sum - 1, b, a, c);
        }
    }

    /**
     * @description 普利姆算法
     * @author haowanjin
     * @date 2019/9/3 14:27
     * @param graph
     * @param v
     * @return void
     */
    public static void prim(MGraph graph, int v) {
        int[] visited = new int[graph.vertex];
        visited[v] = 1;
        int h1 = -1, h2 = -1;
        int minWeight =10000;
        for (int k = 1; k < graph.vertex; k++) {
            for (int i = 0; i < graph.vertex; i++) {
                for (int j = 0; j < graph.vertex; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("边<" + graph.data[h1] + "，" + graph.data[h2] + "> 权值：" + minWeight);
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}

class MGraph {
    int vertex;
    char[] data;
    int[][] weight;

    public MGraph(int vertex, char[] data, int[][] weight) {
        this.vertex = vertex;
        this.data = Arrays.copyOf(data, vertex);
        this.weight = Arrays.copyOf(weight, vertex);
    }

    public void showGraph() {
        for (int[] ints : weight) {
            System.out.println(Arrays.toString(ints));
        }
    }
}