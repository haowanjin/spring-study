package com.tman.javastudy.bean;

public enum FruitEnum {
    APPLE("red", "www.baidu.com/app1.img"),

    ORANGE("orange","www.alibaba.com/ora.jpg")
    ;


    private final String color;
    private final String img;

    FruitEnum(String color,String img) {
        this.color = color;
        this.img = img;
    }

    public String getColor() {
        return color;
    }

    public String getImg() {
        return img;
    }
}
