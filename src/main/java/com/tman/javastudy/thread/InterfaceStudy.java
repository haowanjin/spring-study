package com.tman.javastudy.thread;

public interface InterfaceStudy extends InterfaceTest1, InterfaceTest2 {
    void fun();
}


interface InterfaceTest1 {
    int a =2;

    void fun1();
}

interface InterfaceTest2{
    int b=3;

    void fun2();
}

class InterfaceImpl implements InterfaceStudy{
    public static void main(String[] args) {
        System.out.println(InterfaceImpl.a);
    }

    @Override
    public void fun() {

    }

    @Override
    public void fun1() {

    }

    @Override
    public void fun2() {

    }
}