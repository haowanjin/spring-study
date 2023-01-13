package com.tman.javastudy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicMain {
    @SuppressWarnings("")
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        RealSubject subject = new RealSubject();
        InvocationHandler ds = new ProxySubject(subject);
        Subject rs = (Subject) Proxy.
                newProxyInstance(subject.getClass().getClassLoader(),
                        subject.getClass().getInterfaces(), ds);
        rs.request();
        System.out.println();
        System.out.println(rs.toString());
        System.out.println();
        System.out.println(rs.hashCode());
    }
    @Deprecated
    public void info() {

    }
}
