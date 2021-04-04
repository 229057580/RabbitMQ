package com.aibin.example.mq.fanout;

import com.aibin.example.mq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class FanoutConsumer3 {

    private static String FANOUT_EXCHANGE = "test_exchange";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //声明通道
        Channel channel = connection.createChannel();
        //将通道绑定交换机 //参数一:交换机名称  参数二 交换机类型 fanout广播类型
        channel.exchangeDeclare(FANOUT_EXCHANGE, "fanout");
        //临时队列
        String queue = channel.queueDeclare().getQueue();
        //绑定交换机和队列
        channel.queueBind(queue, FANOUT_EXCHANGE, "");

        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者3---" + new String(body));
            }
        });
    }
}
