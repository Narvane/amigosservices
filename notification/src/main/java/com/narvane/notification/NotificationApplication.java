package com.narvane.notification;

import com.narvane.amqp.RabbitMqMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.narvane.notification",
                "com.narvane.amqp"
        }
)
@EnableEurekaClient
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

/*    @Bean
    CommandLineRunner commandLineRunner(RabbitMqMessageProducer producer, NotificationConfig notificationConfig) {
        return args ->
                producer.publish(
                    new Person("Gus", 23),
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKey());
    }

    record Person(String name, int age) {

    }*/

}