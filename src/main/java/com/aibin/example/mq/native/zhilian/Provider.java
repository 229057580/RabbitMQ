package com.aibin.example.mq.zhilian;

import com.aibin.example.mq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import java.nio.charset.StandardCharsets;

/**
 * 注册：短信
 * 点对点直连
 * @author aibin
 */
public class Provider {

    private static String QUEQUE_HELLO ="hello";

    public static void  main(String[] args) throws Exception{
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
        //获取链接对象
        Connection connection = ConnectionUtils.getConnection();
        //获取链接中的通道对象
        Channel channel = connection.createChannel();
        //通道绑定对应消息队列
        //参数1,队列名
        //参数2,是否持久化,true持久化 false不持久化
        //为true消息的basicproperties需要设置为MessageProperties.PERSISTENT_TEXT_PLAIN，
        // 如果不设置，消息将会丢失，且消费者也要与生产者一致
        //参数3,是否独占队列(true代表只允许当前的队列可用,false则代表可以被其他队列可用)
        //参数4,是否在消费完成后自动删除队列,false不自动删除
        //参数5,附加参数
        //channel.queueDeclare("hello",false,false,false,null);
        //channel.queueDeclare("hello",true,false,false,null);
        channel.queueDeclare(QUEQUE_HELLO,true,false,true,null);
        //参数1,交换机名称
        //参数2,队列名称
        //参数3,传递消息额外设置
        //参数4,消息的具体内容
        channel.basicPublish("",QUEQUE_HELLO, MessageProperties.PERSISTENT_TEXT_PLAIN,"hello,rabbitmq".getBytes(StandardCharsets.UTF_8));
        //关闭通道
        ConnectionUtils.closeConnectionAndChanel(channel,connection);
    }
}
