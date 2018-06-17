package com.liuran.web.utils.thread;

public class TestVolatile {
    private static volatile int number;

    private void addNumber(){
        number = number + 1;
    }

    private void addNumber(int times){
        for (int i = 0; i < times ; i ++){
            addNumber();
        }
    }

    public static void main(String[] args) {
        testNumber();
    }

    private static void testNumber(){
        for (int i = 0 ; i < 20 ; i ++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new TestVolatile().addNumber(5000);
                }
            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(number);
    }
}
