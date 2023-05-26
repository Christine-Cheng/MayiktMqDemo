package com.mayikt.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/2-8:58
 **/

public class Producer {
    private static final String QUEUE_NAME = "mayikt-queue";
    
    public static void main(String[] args) throws IOException, TimeoutException {
        producer();
    }
    
    public static void producer() throws IOException, TimeoutException {
        //1.创建连接
        Connection connection = RabbitMqConnection.getConnection();
        
        //2.设置通道
        Channel channel = connection.createChannel();
        
        //3.设置消息,向通道里面存入消息
        String msg = "勋哥最帅,找个女朋友";
        System.out.println("生产者msg:" + msg);
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));
        
        //4.关闭通道,连接
        channel.close();
        connection.close();
    }
    
}
