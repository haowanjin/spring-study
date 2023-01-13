package com.tman.javastudy.thread;

public class RunnableDemo01 {
    public static void main(String[] args) {
        MyThread mt = new MyThread();
        new Thread(mt).start();
        new Thread(mt).start();
        new Thread(mt).start();
    }

    public static class MyThread implements Runnable{
        private volatile int ticket = 10;
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName()+" 卖票，ticket = " + ticket--);
                }
            }
        }
    }
}
