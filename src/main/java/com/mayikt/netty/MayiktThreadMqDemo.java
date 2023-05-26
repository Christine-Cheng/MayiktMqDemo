package com.mayikt.netty;

import com.alibaba.fastjson2.JSONObject;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Describe: 利用线程队列 模拟消息的  生产者  消费者
 * @Author Happy
 * @Create 2023/4/1-22:02
 **/
public class MayiktThreadMqDemo {
    
    //设置一个队列
    private static LinkedBlockingDeque<JSONObject> msgsDeque = new LinkedBlockingDeque<>();
    
    public static void main(String[] args) {
        //生产者
        Thread productorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //产生数据
                while (true) {
                    try {
                        JSONObject msgJson = new JSONObject();
                        msgJson.put("userId", "12345");
                        msgsDeque.offer(msgJson); //存入队列
                        Thread.sleep(500);//每隔0.5秒进行产生一个数据.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"生产者");
        productorThread.start();
        
        
        //消费者
        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        JSONObject msg = msgsDeque.poll();
                        if (msg != null) {//从队列中取出数据
                            System.out.println(Thread.currentThread().getName() + "--->" + msg);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"消费者");
        consumerThread.start();
    }
}
