package com.aibin.example.mq.boot.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AskConsumer {

    private Logger log = LoggerFactory.getLogger(AskConsumer.class);

    /**
     * ackMode：MANUAL 手动确认
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "work_ask2"), ackMode = "MANUAL")
    public void listener(String msg, Message message, Channel channel) throws Exception {
        Thread.sleep(2000);
        // 该条消息的消息编号，Long类型，递增的
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            // 模拟处理消息
            log.error("消费者1----" + msg);

            // 处理成功 手动ACK回执
            // arg0：消息编号 递增的
            // arg1：true： 将一次性ACK回执成功所有小于消息编号（deliveryTag）的消息
            //		 false：仅ACK回执成功传入的消息编号（deliveryTag）
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {

            // 当消息处理异常时，将消息重新放回队列，重新排队
            // arg0：消息编号 递增的
            // arg1：true： 将一次性拒绝所有小于消息编号（deliveryTag）的消息
            //		false：仅拒绝传入的消息编号（deliveryTag）
            // arg2：true： 让消息重新回到队列
            // 		false：直接丢弃消息
            channel.basicNack(deliveryTag, false, true);
        }
    }

    /**
     * ackMode：MANUAL 手动确认
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "work_ask2"), ackMode = "MANUAL")
    public void listener2(String msg, Message message, Channel channel) throws Exception {
        // 该条消息的消息编号，Long类型，递增的
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            // 模拟处理消息
            log.error("消费者2----" + msg);

            // 处理成功 手动ACK回执
            // arg0：消息编号 递增的
            // arg1：true： 将一次性ACK回执成功所有小于消息编号（deliveryTag）的消息
            //		 false：仅ACK回执成功传入的消息编号（deliveryTag）
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {

            // 当消息处理异常时，将消息重新放回队列，重新排队
            // arg0：消息编号 递增的
            // arg1：true： 将一次性拒绝所有小于消息编号（deliveryTag）的消息
            //		false：仅拒绝传入的消息编号（deliveryTag）
            // arg2：true： 让消息重新回到队列
            // 		false：直接丢弃消息
            channel.basicNack(deliveryTag, false, true);
        }
    }

}
