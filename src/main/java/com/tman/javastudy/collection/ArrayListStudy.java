package com.tman.javastudy.collection;

import java.util.ArrayList;
import java.util.List;

public class ArrayListStudy {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(16);
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.add(1, "ddd");
    }
}
