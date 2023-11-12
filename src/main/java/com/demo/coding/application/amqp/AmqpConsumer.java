package com.demo.coding.application.amqp;

import com.demo.coding.application.properties.RabbitMqDemoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AmqpConsumer {

    private final RabbitMqDemoProperties mqDemoProperties;

    @RabbitListener(queues = "demo.queue")
    public void messageHandle1(String message) {
        System.out.printf("[Rabbitlistener] %s get message: %s", mqDemoProperties.getQueue(), message);
    }

    public void messageHandle2(String message) {
        System.out.printf("[Integrationflow] %s get message: %s", mqDemoProperties.getQueue(), message);
    }
}
