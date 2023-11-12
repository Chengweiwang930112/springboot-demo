package com.demo.coding.application.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMqDemoProperties {
    int port;
    String host;
    String username;
    String password;
    String publisherConfirmType;
    boolean publisherReturns;
    String virtualHost;
    String queue;
    String queueDeadLetter;
    String exchange;
}
