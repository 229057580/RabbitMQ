package com.aibin.example.mq.workqueue;

import com.aibin.example.mq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;

public class WorkQueueProvider {

    private static String WORKQUEUE_HELLO = "workqueue_hello";

    public static void main(String[] args) throws Exception {
        //获取连接对象
        Connection connection = ConnectionUtils.getConnection();
        //声明通道
        Channel channel = connection.createChannel();
        //通过通道声明队列
        channel.queueDeclare(WORKQUEUE_HELLO, true, false, false, null);
        //发布(生产)消息
        for (int i = 1; i <= 10; i++) {
            channel.basicPublish("", WORKQUEUE_HELLO, MessageProperties.PERSISTENT_TEXT_PLAIN, (i + "hello work queue").getBytes(StandardCharsets.UTF_8));
        }
        //关闭资源
        ConnectionUtils.closeConnectionAndChanel(channel, connection);

    }
}
