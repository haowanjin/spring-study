package com.tman.javastudy.collection;

import java.util.*;

public class MapStudy {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new LinkedHashMap<>();
        map.put(11, 11);
        map.put(22, 22);
        map.put(44, 444);
        map.put(33, 33);
        map.put(55, 55);
        Set<Integer> keys = map.keySet();
        keys.size();
        List<Integer> list = new ArrayList<>(keys);
        Collections.sort(list);
        System.out.println();
        for (Integer key : list) {
            System.out.println(map.get(key));
        }


        Stack<Character> stack = new Stack<>();
        if(!stack.contains('a')){

        }
    }

    public static String getResult(String input) {
        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<>();
        String[] arr = input.split("\\s");
        for (String str : arr) {
            stack.push(str);
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        return sb.toString();

    }


}
