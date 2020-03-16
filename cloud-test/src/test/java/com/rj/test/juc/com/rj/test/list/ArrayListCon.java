package com.rj.test.juc.com.rj.test.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @Author: rj
 * @Date: 2020-03-15 10:03
 * @Version: 1.0
 */
public class ArrayListCon {
    @Test
    public void testConcurrent(){
        List<String> list = new ArrayList<>();
//        System.out.println(UUID.randomUUID().toString());
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },"线程"+i).start();
        }

    }

    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

//        System.out.println(UUID.randomUUID().toString());
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },"线程"+i).start();
        }
    }
}
