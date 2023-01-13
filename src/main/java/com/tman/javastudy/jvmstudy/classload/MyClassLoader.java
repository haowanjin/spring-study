package com.tman.javastudy.jvmstudy.classload;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 自定义类加载
 */
public class MyClassLoader extends ClassLoader {
    private String classLoaderName;//自定义ClassLoader的名称
    private String fileExtension = ".class";
    private String path;

    public MyClassLoader(ClassLoader parent, String classLoaderName) {
        super(parent);//指定父加载器
        this.classLoaderName = classLoaderName;
    }

    public MyClassLoader(String classLoaderName) {
        super();//将系统类加载器(AppClassLoader)作为该类加载器的父加载器,getSystemClassLoader()
        this.classLoaderName = classLoaderName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "MyClassLoader{" +
                "classLoaderName='" + classLoaderName + '\'' +
                '}';
    }

    @Override
    protected Class<?> findClass(String className) {
        System.out.println("myClassLoader findClass invoked " + className);
        System.out.println("classLoader name is " + this.classLoaderName);
        byte[] data = this.loadClassData(className);
        return this.defineClass(className, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {//加载类的class字节文件
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream bos = null;
        try {
            name = name.replace(".", "\\");
            is = new FileInputStream(path + name + this.fileExtension);
            bos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = is.read())) {
                bos.write(ch);
            }
            data = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static void test(ClassLoader classLoader, String className) throws Exception {
        Class<?> clazz = classLoader.loadClass(className);
        Object obj = clazz.newInstance();
        System.out.println("类加载器为：" + obj.getClass().getClassLoader());
        System.out.println(clazz.hashCode());
        System.out.println(obj);
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader loader1 = new MyClassLoader("loader1");
        loader1.setPath("E:\\");
        test(loader1, "com.tman.javastudy.jvmstudy.MyTest1");
        System.out.println("~~~~~~~~~~~~~~");
        MyClassLoader loader2 = new MyClassLoader(loader1,"loader2");
        loader2.setPath("E:\\");
        test(loader2, "com.tman.javastudy.jvmstudy.MyTest1");
    }

}
