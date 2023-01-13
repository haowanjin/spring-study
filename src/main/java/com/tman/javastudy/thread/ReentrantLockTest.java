package com.tman.javastudy.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            System.out.println("A 执行任务");
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "A").start();
        new Thread(() -> {
            lock.lock();
            System.out.println("B 执行任务");
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "B").start();
    }
}
