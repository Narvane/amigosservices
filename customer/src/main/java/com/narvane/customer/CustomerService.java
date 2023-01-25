package com.narvane.customer;

import com.narvane.amqp.RabbitMqMessageProducer;
import com.narvane.clients.fraud.FraudCheckResponse;
import com.narvane.clients.fraud.FraudClient;
import com.narvane.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMqMessageProducer rabbitMqMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // TODO: email valid
        // TODO email not taken
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                "Welcome to the system!"
        );

        if (!fraudCheckResponse.isFraudster()) {
            rabbitMqMessageProducer.publish(
                    notificationRequest,
                    "internal.exchange",
                    "internal.notification.routing-key"
            );
        }
    }

}
