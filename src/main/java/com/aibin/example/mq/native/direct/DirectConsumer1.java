package com.aibin.example.mq.direct;

import com.aibin.example.mq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class DirectConsumer1 {

    private static String DIRECT_EXCHANGE = "direct_exchange";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //声明通道
        Channel channel = connection.createChannel();
        //声明交换机以及交换机类型为direct
        channel.exchangeDeclare(DIRECT_EXCHANGE, "direct");
        //获取临时队列
        String queue = channel.queueDeclare().getQueue();
        //临时队列和交换机绑定
        channel.queueBind(queue, DIRECT_EXCHANGE, "direct_info");
        channel.queueBind(queue, DIRECT_EXCHANGE, "direct_error");
        channel.queueBind(queue, DIRECT_EXCHANGE, "direct_warn");
        //接收消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1----" + new String(body));
            }
        });


    }
}
