package com.tman.javastudy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxySubject implements InvocationHandler {
    private Object sub;

    public ProxySubject(Object sub) {
        this.sub = sub;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before calling " + method.getName());
        method.invoke(sub, args);
        System.out.println("after calling " + method.getName());
        return null;
    }
}
