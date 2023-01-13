package com.tman.javastudy.jvmstudy.classload;

/**
 * 《命名空间》
 * 1、子加载器所加载的类能够访问到父加载器所加载的类
 * 2、父加载器所加载的类无法访问到子加载器所加载的类
 * 3、若两个加载没有直接或间接的父子关系，那么它们所加载的类也是不可见的
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        /*MyClassLoader loader1 = new MyClassLoader("loader1");
        MyClassLoader loader2 = new MyClassLoader("loader2");

        Class<?> clazz1 = loader1.loadClass("com.tman.javastudy.jvmstudy.MyPerson");
        Class<?> clazz2 = loader1.loadClass("com.tman.javastudy.jvmstudy.MyPerson");

        System.out.println(clazz1 == clazz2);//true,因为都是系统类加载器加载的

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        method.invoke(object1, object2);
        System.out.println(ClassLoader.getSystemClassLoader());*/
        String[] a = new String[0];
        System.out.println(a.getClass().getClassLoader());

    }
}

