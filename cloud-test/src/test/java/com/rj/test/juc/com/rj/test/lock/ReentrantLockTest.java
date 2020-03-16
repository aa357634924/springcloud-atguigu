package com.rj.test.juc.com.rj.test.lock;

/**
 * @Author: rj
 * @Date: 2020-03-15 12:05
 * @Version: 1.0
 */
public class ReentrantLockTest {
    public synchronized void tes1(){
        System.out.println(Thread.currentThread().getName()+"方法1");
        test2();
    }

    public synchronized void test2(){
        System.out.println(Thread.currentThread().getName()+"方法2");
    }

    public static void main(String[] args) {
        ReentrantLockTest lock = new ReentrantLockTest();
        for (int i = 1; i < 6; i++) {
            new Thread(()->{
                lock.tes1();
            },CountryEnum.getCountry(i).getName()).start();
        }
    }

}
