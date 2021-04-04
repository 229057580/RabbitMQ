package com.aibin.example.mq.boot.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkConsumer {

    private Logger log = LoggerFactory.getLogger(WorkConsumer.class);

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void receive1(String msg){
        log.error("work消费者1--"+msg);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void receive2(String msg){
        log.error("work消费者2--"+msg);
    }
}
