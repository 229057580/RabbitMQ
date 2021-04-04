package com.aibin.example.mq.topics;

import com.aibin.example.mq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class TopicConsumer2 {

    private static String TOPIC_EXCHANGE = "topic_exchange";

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //声明交换机以及类型
        channel.exchangeDeclare(TOPIC_EXCHANGE, "topic");
        //绑定交换机 test.#=test.*.*.*......可以匹配多个
        channel.queueBind(queue, TOPIC_EXCHANGE, "test.#");
        //接收消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2---" + new String(body));
            }
        });
    }
}
