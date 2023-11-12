package com.demo.coding;

import com.demo.coding.application.properties.RabbitMqDemoProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RabbitMqDemoProperties.class)
public class CodingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodingApplication.class, args);
    }

}
