package com.aibin.example.mq.topics;

import com.aibin.example.mq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Provider {

    private static String TOPIC_EXCHANGE = "topic_exchange";

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //声明通道
        Channel channel = connection.createChannel();
        //声明通道
        channel.exchangeDeclare(TOPIC_EXCHANGE, "topic");
        //动态路由
        String routingKey = "test.save.a";
        //发布消息
        channel.basicPublish(TOPIC_EXCHANGE, routingKey, null, ("topic--" + routingKey).getBytes(StandardCharsets.UTF_8));
        //关闭资源
        ConnectionUtils.closeConnectionAndChanel(channel, connection);
    }
}
