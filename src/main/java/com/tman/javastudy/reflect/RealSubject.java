package com.tman.javastudy.reflect;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("From Real Subject");
    }

    @Override
    public String toString() {
        System.out.println("被代理的toString");
        return "RealSubject{}";
    }
}
