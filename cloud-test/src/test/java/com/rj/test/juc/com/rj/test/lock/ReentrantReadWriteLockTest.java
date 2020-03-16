package com.rj.test.juc.com.rj.test.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: rj
 * @Date: 2020-03-15 14:11
 * @Version: 1.0
 * writeLock 独占锁，中途不能被打扰
 * readLock 共享锁，可同时使用
 *
 */
public class ReentrantReadWriteLockTest {
    ReentrantReadWriteLock rrw = new ReentrantReadWriteLock();
    Map<String,String> map = new HashMap<>();
    public void put(String key,String value){
        rrw.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在写入。。。");
            try{TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e) {e.printStackTrace();}
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rrw.writeLock().unlock();
        }
    }
    public void get(String key){
        rrw.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在读取。。。");
            try{TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e) {e.printStackTrace();}
            String value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成:"+value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rrw.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockTest obj = new ReentrantReadWriteLockTest();
        for (int i = 0; i < 3; i++) {
            final int n = i;
            new Thread(()->{
                obj.put(n+"",n+"");
            },"线程"+i).start();
        }
        for (int i = 1; i < 6; i++) {
            final int n = i;
            new Thread(()->{
                obj.get(n+"");
            },"线程"+CountryEnum.getCountry(n).getName()).start();
        }

    }
}
