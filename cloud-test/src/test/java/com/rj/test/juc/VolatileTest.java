package com.rj.test.juc;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: rj
 * @Date: 2020-03-13 18:08
 * @Version: 1.0
 */
public class VolatileTest {


    public static void main(String[] args) {

    }

    /**
     * @Author: rj
     * @Date: 2020-03-13
     * @Description: 验证 volatile 不保证原子性
     * @Param:
     * @Return:
     * @Version 1.0
     */
    @Test
    public void testAtomic(){
        MyData obj = new MyData();
        for (int i = 1; i <=20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    obj.addPlusPlus();
                    obj.addGanGet();
                }
            }, "线程" + i).start();
        }
        // activeCount > 2 主线程、gc线程 ，如果 大于2  说明还没有计算完毕
        while (Thread.activeCount() > 2){
            // 线程让出当前时间片给其他线程执行
            Thread.yield();
        }
        System.out.println("结算结果为："+obj.n);
        System.out.println("结算结果为 atomic："+obj.num);
    }

    /**
     * @Author: rj
     * @Date: 2020-03-13
     * @Description: 验证volatile 保证内存可见性
     * @Param:
     * @Return:
     * @Version 1.0
     */
    @Test
    public void testSeeOk() {
        MyData obj = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t update n value:" + obj.n);
        }, "ThreadA").start();
        while (obj.n == 0) {

        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over,Mydata.n=" + obj.n);
    }
}

class MyData {
    volatile int n = 0;

    public void addTo60() {
        this.n = 60;
    }

    public void addPlusPlus(){
        n++;
    }

    AtomicInteger num = new AtomicInteger();

    public void addGanGet(){
        num.getAndIncrement();
    }
}