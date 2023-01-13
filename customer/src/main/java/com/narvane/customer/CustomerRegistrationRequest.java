package com.narvane.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
