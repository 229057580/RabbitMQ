package com.aibin.example.mq.boot.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkConsumer {

    private Logger log = LoggerFactory.getLogger(WorkConsumer.class);

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void receive1(String msg){
        log.error("work消费者1--" + msg);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void receive2(String msg){
        log.error("work消费者2--" + msg);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "work_ask"))
    public void receive3(String msg, Message message, Channel channel) throws Exception {
        try {
            Thread.sleep(3000);
            //确认一条消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.error("DirectReceiver消费者3收到消息  : " + msg);
        } catch (Exception e) {
            //消费者告诉队列信息消费失败
            /**
             * 拒绝确认消息:
             * channel.basicNack(long deliveryTag, boolean multiple, boolean requeue) ;
             * deliveryTag:该消息的index
             * multiple：是否批量true:将一次性拒绝所有小于deliveryTag的消息
             * requeue：被拒绝的是否重新入队列
             */
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "work_ask"))
    public void receive4(String msg, Message message, Channel channel) throws Exception {
        try {
            //确认一条消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.error("DirectReceiver消费者4收到消息  : " + msg);
        } catch (Exception e) {
            //消费者告诉队列信息消费失败
            /**
             * 拒绝确认消息:
             * channel.basicNack(long deliveryTag, boolean multiple, boolean requeue) ;
             * deliveryTag:该消息的index
             * multiple：是否批量true:将一次性拒绝所有小于deliveryTag的消息
             * requeue：被拒绝的是否重新入队列
             */
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}
