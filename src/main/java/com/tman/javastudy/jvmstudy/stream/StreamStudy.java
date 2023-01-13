package com.tman.javastudy.jvmstudy.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author wanjin.hao@woqutech.com
 * @Date 2020/11/16
 */
public class StreamStudy {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> list2 = Arrays.asList(1,3, 54, 5, 55);
        list.stream().forEach(e->{
            System.out.println(e);
            System.out.println("every thing end");
        });

        List<Integer> collect = list.stream().filter(e -> list2.contains(e)).collect(Collectors.toList());
        System.out.println(collect);
    }
}
