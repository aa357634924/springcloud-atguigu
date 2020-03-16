package com.rj.test.juc.com.rj.test.singleTon;

/**
 * @Author: rj
 * @Date: 2020-03-13 21:57
 * @Version: 1.0
 */
public class SingleTonTest {
    // 使用volatile保证内存可见性，禁止指令重排
    private static volatile SingleTonTest singleTonTest = null;

    public SingleTonTest(){
        System.out.println("实例化对象");
    }

    // DCL-- Double Check Lock 双端检索机制
    public static SingleTonTest getInstance(){
        if(null == singleTonTest){
            synchronized (SingleTonTest.class){
                if(null == singleTonTest){
                    singleTonTest = new SingleTonTest();
                }
            }
        }
        return singleTonTest;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                getInstance();
            },"线程"+i).start();
        }
    }
}
