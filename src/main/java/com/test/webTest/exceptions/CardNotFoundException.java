package com.test.webTest.exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String cardNumber) {
        super("Card with number " + cardNumber + " not found.");
    }
}
