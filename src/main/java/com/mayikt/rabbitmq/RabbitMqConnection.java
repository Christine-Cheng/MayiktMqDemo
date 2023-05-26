package com.mayikt.rabbitmq;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/2-8:58
 **/
public class RabbitMqConnection {
    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        // 1.创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2.设置连接地址
        connectionFactory.setHost("127.0.0.1");
        // 3.设置端口号:
        connectionFactory.setPort(5672);
        // 4.设置账号和密码
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        // 5.设置VirtualHost
        connectionFactory.setVirtualHost("/meiteVirtualHosts");
        return connectionFactory.newConnection();
    }
    
}
