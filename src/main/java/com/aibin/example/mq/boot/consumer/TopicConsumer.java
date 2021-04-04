package com.aibin.example.mq.boot.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {
    private Logger log = LoggerFactory.getLogger(TopicConsumer.class);

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,//临时队列
                    exchange = @Exchange(type = "topic", value = "topic_test"),
                    key = {"user.#","order.*"})
    })
    public void receive1(String msg) {
        log.error("topic消费者1---" + msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,//临时队列
                    exchange = @Exchange(type = "topic", value = "topic_test"),
                    key = {"user.save"})
    })
    public void receive2(String msg) {
        log.error("topic消费者2---" + msg);
    }
}
