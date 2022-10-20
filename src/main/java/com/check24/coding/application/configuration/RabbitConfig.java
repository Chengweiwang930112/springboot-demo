package com.check24.coding.application.configuration;

import com.check24.coding.application.amqp.AmqpConsumer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;

@Configuration
public class RabbitConfig {

    @Autowired
    private ConnectionFactory rabbitConnectionFactory;

    @Autowired
    private RabbitProperties properties;

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitConnectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setMandatory(true);
        //rabbitTemplate.setRetryTemplate(rabbitRetryTemplate());
        rabbitTemplate.setConfirmCallback(
                ((correlationData, b, s) -> {
                    System.out.println("ConfirmCallback data: "+ correlationData);
                    System.out.println("ConfirmCallback ack: "+b);
                    System.out.println("Confirmcallback cause: "+s);
                })
        );
        rabbitTemplate.setReturnsCallback(returnedMessage -> System.out.println("Message was returned: "+returnedMessage));
        return rabbitTemplate;
    }

    @Bean
    public IntegrationFlow mqInboundFlow(final AmqpConsumer consumer){
        return IntegrationFlows.from(
                Amqp.inboundAdapter(rabbitConnectionFactory, "queue.direct"))
                .transform(Transformers.fromJson(String.class))
                .handle(consumer, "messageHandle2")
                .get();
    }
}
