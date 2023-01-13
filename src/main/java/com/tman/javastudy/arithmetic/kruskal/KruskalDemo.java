package com.tman.javastudy.arithmetic.kruskal;

import java.util.Arrays;

class KruskalDemo {
    private int edgeNum;
    private char[] vertexes;
    private int[][] matrix;
    private final static int INF = Integer.MAX_VALUE;

    public KruskalDemo(char[] vertexes, int[][] matrix) {
        this.vertexes = Arrays.copyOf(vertexes, vertexes.length);
        this.matrix = Arrays.copyOf(matrix, matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i][j] != INF) {
                    this.edgeNum++;
                }
            }
        }
    }

    public int getPosition(char taget) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == taget) {
                return i;
            }
        }
        return -1;
    }

    public EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexes[i], vertexes[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    public int getEnd(int[] ends, int p) {
        while (ends[p] != 0) {
            p = ends[p];
        }
        return p;
    }

    private void sort(EData[] edges) {
        for (int i = 0; i < edgeNum - 1; i++) {
            for (int j = i + 1; j > 0 && edges[j].weight < edges[j - 1].weight; j--) {
                EData tmp = edges[j];
                edges[j] = edges[j - 1];
                edges[j - 1] = tmp;
            }
        }
    }

    public void kruskal() {
        int index = 0;
        EData[] res = new EData[edgeNum];
        int[] ends = new int[edgeNum];
        EData[] edges = getEdges();//获取边的集合
        sort(edges);

        for (int i = 0; i < edgeNum; i++) {
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            if (m != n) {
                ends[m] = n;
                res[index++] = edges[i];
            }
        }
        for (int i = 0; i < index; i++) {
            System.out.println(res[i]);
        }
    }
}