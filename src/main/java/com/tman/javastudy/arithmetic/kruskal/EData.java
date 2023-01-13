package com.tman.javastudy.arithmetic.kruskal;

class EData {
    protected char start;
    protected char end;
    protected int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "<" +
                start +
                "," + end +
                "> = " + weight;
    }
}