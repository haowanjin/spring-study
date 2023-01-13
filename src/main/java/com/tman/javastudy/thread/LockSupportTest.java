package com.tman.javastudy.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("A 执行程序");
            try {
                System.out.println("T1将被 park");
                TimeUnit.SECONDS.sleep(1);
                LockSupport.park("AAAAA");
                System.out.println("T1被 unPark");
            } catch (InterruptedException e) {
                System.out.println("A 被中断");
                System.out.println(Thread.currentThread().getName() + "响应中断");
            }
            System.out.println("中断后还能执行吗");
        }, "A");
        t1.start();
        Thread.sleep(5000*2);
//        LockSupport.unpark(t1);
    }
}
