package com.aibin.example.mq.boot.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutConsumer {

    private Logger log = LoggerFactory.getLogger(FanoutConsumer.class);

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,//临时队列
                    exchange = @Exchange(value = "fanout_test",type = "fanout"//绑定交换机，并制定类型
                    )
            )
    })
    public void receive1(String msg){
        log.error("fanout消费者1----"+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,//临时队列
                    exchange = @Exchange(value = "fanout_test",type = "fanout"//绑定交换机，并制定类型
                    )
            )
    })
    public void receive2(String msg){
        log.error("fanout消费者2----"+msg);
    }
}
