package com.narvane.customer;

import com.narvane.clients.fraud.FraudCheckResponse;
import com.narvane.clients.fraud.FraudClient;
import com.narvane.clients.notification.NotificationClient;
import com.narvane.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

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

        // TODO: make it async add to queue
        if (!fraudCheckResponse.isFraudster()) {
            notificationClient.sendNotification(
                    new NotificationRequest(
                            customer.getId(),
                            customer.getEmail(),
                            "Welcome to the system!"
                    )
            );
        }
    }

}
