package com.mayikt.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/2-8:57
 **/
@Component
public class Consumer {
    private static final String QUEUE_NAME = "mayikt-queue";
    
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1.创建连接
        Connection connection = RabbitMqConnection.getConnection();
        // 2.设置通道
        Channel channel = connection.createChannel();
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("消费者获取msg:" + msg);
            }
        };
        //3.监听队列
        //autoAck = true 自动签收消息
        //autoAck = false 手动签收消息
        //一般情况都是手动进行消息签收,相当于消费者没有报错的情况下才告诉MQ消息消费成功
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);
        
    }
    
    
}
