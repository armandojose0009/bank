package com.test.webTest.exceptions;

public class CardAlreadyExistsException extends RuntimeException {
    public CardAlreadyExistsException(String cardNumber) {
        super("Card with number " + cardNumber + " already assigned.");
    }
}
