package com.aibin.example.mq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * rabbitMq工具类
 */
public class ConnectionUtils {

    private static ConnectionFactory connectionFactory;

    static {
        //创建连接mq的工处对象（在类加载的时候就进行创建）
        connectionFactory = new ConnectionFactory();
        //设置链接mq的主机ip
        connectionFactory.setHost("127.0.0.1");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置链接哪个虚拟主机(类似于数据库中的库)
        connectionFactory.setVirtualHost("/test");
        //设置用户名密码
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
    }

    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            //创建连接对象
            Connection connection = connectionFactory.newConnection();

            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭资源
     *
     * @param channel
     * @param connection
     */
    public static void closeConnectionAndChanel(Channel channel, Connection connection) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
