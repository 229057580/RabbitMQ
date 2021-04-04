package com.aibin.example.mq.boot.provider;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注意：也可以使用@configration +@bean的方式提前定义好队列,交换机,以及绑定等。
 */
@RestController
public class TestProviderController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 直连
     */
    @GetMapping("/sendHelloWorld")
    public void sendHelloWorld() {
        //队列名称 ,消息对象
        rabbitTemplate.convertAndSend("hello", "hello world");
    }

    /**
     * work模式
     */
    @GetMapping("/sendWork")
    public void sendWork() {
        for (int i = 1; i <= 10; i++) {
            //队列名称
            rabbitTemplate.convertAndSend("work", "work queue----" + i);
        }
    }

    /**
     * work模式
     */
    @GetMapping("/sendWorkAsk")
    public void sendWorkAsk() {
        for (int i = 1; i <= 30; i++) {
            //队列名称
            rabbitTemplate.convertAndSend("work", "work queue----" + i);
        }
    }

    /**
     * fanout 广播
     */
    @GetMapping("/sendFanout")
    public void sendFanout() {
        //队列名称 ,消息对象
        rabbitTemplate.convertAndSend("fanout_test", "", "fanout queue");
    }

    /**
     * direct 路由模式
     */
    @GetMapping("/sendDirect")
    public void sendDirect() {
        //队列名称 ,消息对象
        rabbitTemplate.convertAndSend("direct_test", "error", "direct queue");
    }

    /**
     * topic 动态路由(订阅模式)
     */
    @GetMapping("/sendTopic")
    public void sendTopic() {
        //队列名称 ,消息对象
        rabbitTemplate.convertAndSend("topic_test", "user.save", "topic queue");
    }
}
