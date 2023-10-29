package com.demo.coding.application.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AmqpConsumer {

    @RabbitListener(queues = "queue.direct")
    public void messageHandle1(String message){
        System.out.println("[Rabbitlistener] queue.direct get message: "+message);
    }

    public void messageHandle2(String message){
        System.out.println("[Integrationflow] queue.direct get message: "+message);
    }
}
