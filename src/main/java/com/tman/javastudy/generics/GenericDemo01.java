package com.tman.javastudy.generics;

public class GenericDemo01 {

    public static void main(String[] args) {
        Point<String> p = new Point<>("A");
        Point<Integer> p1 = new Point<>(12);
        fun(p);
        fun(p1);
    }

    public static void fun(Point<? extends Object> o) {
        System.out.println(o.var);
    }

    static class Point<T>{
        T var;
        public Point(T var) {
            this.var=var;
        }
    }

    interface China{
        String NATIONAL = "CHINA";
    }
}
