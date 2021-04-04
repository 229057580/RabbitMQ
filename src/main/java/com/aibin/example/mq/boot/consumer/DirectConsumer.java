package com.aibin.example.mq.boot.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectConsumer {

    private Logger log = LoggerFactory.getLogger(DirectConsumer.class);

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,//临时队列
            exchange = @Exchange(value = "direct_test",type = "direct"),
            key={"info","error","warn"}
    ))
    public void receive1(String msg){
        log.error("direct消费者1---"+msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,//临时队列
            exchange = @Exchange(value = "direct_test",type = "direct"),
            key={"info"}
    ))
    public void receive2(String msg){
        log.error("direct消费者1---"+msg);
    }
}
