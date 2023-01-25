package com.narvane.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.narvane.customer",
                "com.narvane.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.narvane.clients"
)
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class);
    }
}
