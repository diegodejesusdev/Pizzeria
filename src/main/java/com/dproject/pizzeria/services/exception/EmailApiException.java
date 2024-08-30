package com.dproject.pizzeria.services.exception;

public class EmailApiException extends RuntimeException {
    public EmailApiException() {
        super("Error sending email!!");
    }
}
