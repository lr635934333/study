package com.liuran.web.utils.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * synchronized解析
 * A public synchronized static void method(){} 不同实例间多线程串行执行
 * B public synchronized void method() 相同实例多线程串行执行,不同实例间多线程并发执行
 * C synchronized(this) 当前实例做为一把锁,效果和B相同
 * D synchronized(TestSynchronized.class) 这个类作为一把锁,因此无论多少实例都自由一把锁,效果和A相同
 * E synchronized(lock) 指定一个对象(这个lock是可以变化的,如:资源ID)作为一把锁,这种方式灵活性更好,可以提升并发性能
 * */
public class TestSynchronized {

    public static Map<String, Integer> map = new HashMap<>();
    private String name;
    private TestSynchronized(String name){
        this.name = name.intern();
    }

    public void add(){
        synchronized (lock()){
            Integer old = map.get(name) == null ? 0 : map.get(name);
            map.put(name, old + 1);
        }
    }

    public void add(int times){
        for (int i=0; i < times ;i ++){
            add();
        }
    }

    public static void printMap(){
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    /**
     * this.getClass().getName() 生成对象唯一的lock字符串
     * str.intern()字符串添加到常量池,相同的字符串都是同一个对象
     * */
    private String lock(){
        return (this.getClass().getName() + name).intern();
    }

    public static void main(String[] args) {
        for (int i = 0 ;i < 10 ;i ++){
            final int index = i;
            TestSynchronized test = new TestSynchronized("thread" + index);
            for (int j = 0; j < 10 ;j ++){
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        test.add(1000);
                    }
                });
                thread.start();
            }

        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printMap();
    }
}
