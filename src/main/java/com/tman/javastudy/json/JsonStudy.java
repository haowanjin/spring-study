package com.tman.javastudy.json;

import com.alibaba.fastjson.JSONArray;
import com.tman.javastudy.bean.Lover;

import java.util.List;

public class JsonStudy {
    public static void main(String[] args) {
        String str = "[{'name':'haowanjin','age':25,'addr':'hangzhou'},{'name':'wenzhuyu','age':23,'addr':'chongqing'}]";
        List<Lover> lovers = JSONArray.parseArray(str, Lover.class);

        System.out.println(lovers.toArray());

    }
}
