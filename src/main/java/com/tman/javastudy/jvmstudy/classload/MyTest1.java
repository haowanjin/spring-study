package com.tman.javastudy.jvmstudy.classload;

/**
 * 对于静态字段来说，只有直接定义了该字段的类才会被初始化
 * 当一个类在初始化时，要求其父类全部都已经初始化完毕了
 * <p>
 * -XX:+TraceClassLoading,用于追踪类的加载信息并打印出来
 * -XX:+<option>,表示开启一个选项
 * -XX:-<option>,表示关闭一个选项
 * -XX:<option>=<value>，表示将option选项的值设置为value
 * <p>
 * System.out.println(MyChild1.str);输出结果为：
 * This is parent static block code
 * hello world
 * ---------------------------
 * System.out.println(MyChild1.str2);输出结果为
 * This is parent static block code
 * This is child static block code
 * welcome
 */
public class MyTest1 {
    public static void main(String[] args) {
        System.out.println(MyChild1.str);
    }

    static class MyParent1 {
        public static String str = "hello world";

        static {
            System.out.println("this is parent static block code");
        }
    }

    static class MyChild1 extends MyParent1 {
        public static String str2 = "welcome";

        static {
            System.out.println("This is child static block code");
        }
    }
}
