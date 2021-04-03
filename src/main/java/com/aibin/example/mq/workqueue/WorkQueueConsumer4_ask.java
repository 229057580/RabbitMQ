package com.aibin.example.mq.workqueue;

import com.aibin.example.mq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class WorkQueueConsumer4_ask {

    private static String WORKQUEUE_HELLO ="workqueue_hello";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //声明通道
        final Channel channel = connection.createChannel();
        //一次只能消费一个消息
        channel.basicQos(1);
        //通过通道声明队列
        channel.queueDeclare(WORKQUEUE_HELLO, true, false, false, null);
        //接收消息
        //参数一:消费哪个队列 队列名称
        //参数二:开启消息的确认机制 true表示消费者自动向rabbitmq确认消息，false表示不会自动确认
        //参数三:消费时的回调接口
        channel.basicConsume(WORKQUEUE_HELLO, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1----"+new String(body));
                //参数1：确认消息中的哪个消息 参数2:是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
