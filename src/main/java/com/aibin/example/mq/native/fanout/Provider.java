package com.aibin.example.mq.fanout;

import com.aibin.example.mq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.nio.charset.StandardCharsets;

public class Provider {

    private static String FANOUT_EXCHANGE ="test_exchange";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //声明通道
        Channel channel = connection.createChannel();
        //将通道绑定交换机 //参数一:交换机名称  参数二 交换机类型 fanout广播类型
        channel.exchangeDeclare(FANOUT_EXCHANGE,"fanout");
        //发送消息
        channel.basicPublish(FANOUT_EXCHANGE,"",null,"fanout".getBytes(StandardCharsets.UTF_8));
        //关闭通道
        ConnectionUtils.closeConnectionAndChanel(channel,connection);
    }
}
