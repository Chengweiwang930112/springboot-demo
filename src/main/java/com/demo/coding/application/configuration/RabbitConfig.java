package com.demo.coding.application.configuration;

import com.demo.coding.application.amqp.AmqpConsumer;
import com.demo.coding.application.properties.RabbitMqDemoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    private final ConnectionFactory rabbitConnectionFactory;
    private final RabbitProperties properties;
    private final RabbitMqDemoProperties rabbitMqProperties;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitConnectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setMandatory(true);
        //rabbitTemplate.setRetryTemplate(rabbitRetryTemplate());
        rabbitTemplate.setConfirmCallback(
                ((correlationData, b, s) -> {
                    System.out.println("ConfirmCallback data: " + correlationData);
                    System.out.println("ConfirmCallback ack: " + b);
                    System.out.println("Confirmcallback cause: " + s);
                })
        );
        rabbitTemplate.setReturnsCallback(returnedMessage -> System.out.println("Message was returned: " + returnedMessage));
        return rabbitTemplate;
    }

    @Bean
    public IntegrationFlow mqInboundFlow(final AmqpConsumer consumer) {
        return IntegrationFlows.from(
                        Amqp.inboundAdapter(rabbitConnectionFactory, rabbitMqProperties.getQueue()))
                .transform(Transformers.fromJson(String.class))
                .handle(consumer, "messageHandle2")
                .get();
    }
}
