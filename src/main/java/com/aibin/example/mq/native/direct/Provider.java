package com.aibin.example.mq.direct;

import com.aibin.example.mq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Provider {

    private static String DIRECT_EXCHANGE ="direct_exchange";

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //通过连接获取通道
        Channel channel = connection.createChannel();
        //通过通道声明交换机
        channel.exchangeDeclare(DIRECT_EXCHANGE,"direct");
        String routingKey ="direct_info";
        channel.basicPublish(DIRECT_EXCHANGE,routingKey,null,("direct"+routingKey).getBytes(StandardCharsets.UTF_8));
        ConnectionUtils.closeConnectionAndChanel(channel,connection);
    }
}
