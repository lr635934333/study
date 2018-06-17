package com.liuran.web.utils.thread;

public class TestThreadLocal {
    private static ThreadLocal<String> threadLocalName = new ThreadLocal<>();
    private static ThreadLocal<String> threadLocalPassword = new ThreadLocal<>();
    private static ThreadLocal<String> threadLocalSession = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                test();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test();
            }
        }).start();
    }

    public static void test(){
        Thread t;
        threadLocalName.set("LR");
//        threadLocalPassword.set("password");
//        threadLocalSession.set("session");
        System.out.println(threadLocalName.get());
    }
}
