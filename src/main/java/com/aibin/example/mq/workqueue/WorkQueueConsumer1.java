package com.aibin.example.mq.workqueue;

import com.aibin.example.mq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class WorkQueueConsumer1 {

    private static String WORKQUEUE_HELLO ="workqueue_hello";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //声明通道
        Channel channel = connection.createChannel();
        //通过通道声明队列
        channel.queueDeclare(WORKQUEUE_HELLO, true, false, false, null);
        //参数一:消费哪个队列 队列名称
        //参数二:开启消息的确认机制 true表示消费者自动确认，false表示不会自动确认
        //参数三:消费时的回调接口
        channel.basicConsume(WORKQUEUE_HELLO, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1----"+new String(body));
            }
        });
    }
}
