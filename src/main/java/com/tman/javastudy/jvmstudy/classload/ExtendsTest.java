package com.tman.javastudy.jvmstudy.classload;

/**
 * 在初始化一个类时，并不会先初始化它所实现的接口。
 * 在初始化一个接口时，并不会先初始化它的父接口。
 */
public class ExtendsTest {
    public static void main(String[] args) {
        System.out.println(MyChild2.a);
        System.out.println(MyParent2_1.thread);
    }
}

interface MyParent2{
    public static final Thread thread = new Thread(){
        {
            System.out.println("myParent2 invoke");
        }
    };
}
class MyChild2 implements MyParent2{
    public static int a= 5;
}

interface MyGrandpa2{
    public static final Thread thread = new Thread(){
        {
            System.out.println("myGrandpa2 invoke");
        }
    };
}
interface MyParent2_1 extends MyGrandpa2{
    public static final Thread thread = new Thread(){
        {
            System.out.println("myGrandpa2 invoke");
        }
    };
}