package com.aibin.example.mq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class ConnectionFactoryUtil {

    public void getConnection()throws Exception{
        //创建连接mq的工处对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置链接mq的主机ip
        connectionFactory.setHost("127.0.0.1");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置链接哪个虚拟主机(类似于数据库中的库)
        connectionFactory.setVirtualHost("/test");
        //设置用户名密码
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        //获取链接对象
        Connection connection = connectionFactory.newConnection();
        //获取链接中的通道对象
        Channel channel = connection.createChannel();
        //通道绑定对应消息队列
        //参数1,队列名
        //参数2,是否持久化,true持计划 false不持久化
        //参数3,是否独占队列(true代表只允许当前的队列可用,false则代表可以被其他队列可用)
        //参数4,是否在消费完成后自动删除队列,false不自动删除
        //参数5,附近参数
        channel.queueDeclare("hello",false,false,false,null);
        //参数1,交换机名称
        //参数2,队列名称
        //参数3,传递消息额外设置
        //参数4,消息的具体内容
        channel.basicPublish("","hello",null,"hello,rabbitmq".getBytes(StandardCharsets.UTF_8));

        channel.close();
        connection.close();
    }
}
