package com.aibin.example.mq.zhilian;

import com.aibin.example.mq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 * 点对点直连
 */
public class Consumer {

    private static String QUEQUE_HELLO ="hello";

    public static void main(String[] args) throws IOException, TimeoutException {
//        //创建连接mq的工处对象
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        //设置链接mq的主机ip
//        connectionFactory.setHost("127.0.0.1");
//        //设置端口号
//        connectionFactory.setPort(5672);
//        //设置链接哪个虚拟主机(类似于数据库中的库)
//        connectionFactory.setVirtualHost("/test");
//        //设置用户名密码
//        connectionFactory.setUsername("admin");
//        connectionFactory.setPassword("admin");
        //创建连接对象
        Connection connection = ConnectionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //通道绑定对应消息队列
        //参数1,队列名
        //参数2,是否持久化,true持久化 false不持久化
        //为true消息的basicproperties需要设置为MessageProperties.PERSISTENT_TEXT_PLAIN，且消费者也要与生产者一致
        //参数3,是否独占队列(true代表只允许当前的队列可用,false则代表可以被其他队列可用)
        //参数4,是否在消费完成后自动删除队列,false不自动删除
        //参数5,附加参数
        //channel.queueDeclare("hello",false,false,false,null);
        //channel.queueDeclare("hello",true,false,false,null);
        channel.queueDeclare(QUEQUE_HELLO,true,false,true,null);
        //消费消息
        //参数一:消费哪个队列 队列名称
        //参数二:开启消息的确认机制 true表示消费者自动确认，false表示不会自动确认
        //参数三:消费时的回调接口
        channel.basicConsume(QUEQUE_HELLO,true,new DefaultConsumer(channel){

            //byte[] body 消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
        //关闭通道
        ConnectionUtils.closeConnectionAndChanel(channel,connection);
    }
}
