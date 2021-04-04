package com.aibin.example.mq.boot.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))
//@RabbitListener(queuesToDeclare = @Queue(value = "hello",declare = "false",autoDelete = "false"))
public class HelloConsumer {

    private Logger log = LoggerFactory.getLogger(HelloConsumer.class);

    @RabbitHandler
    public void receive(String msg){
        log.error("消费者----"+msg);
    }

}
