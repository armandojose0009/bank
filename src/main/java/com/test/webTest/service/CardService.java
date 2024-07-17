package com.test.webTest.service;

import com.test.webTest.exceptions.CardAlreadyExistsException;
import com.test.webTest.exceptions.CardNotFoundException;
import com.test.webTest.model.Card;
import com.test.webTest.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public String generateCardNumber(String productId) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(productId);
        while (sb.length() < 16) {
            sb.append(rand.nextInt(10));
        }

        return sb.toString();
    }

    public Card enrollCard(String cardNumber) {
        Optional<Card> existingCard = cardRepository.findByCardNumber(cardNumber);
        if (existingCard.isPresent()) {
            throw new CardAlreadyExistsException(cardNumber);
        }

        Card newCard = new Card();
        newCard.setCardNumber(cardNumber);
        newCard.setBalance(0);
        newCard.setActive(false);
        return cardRepository.save(newCard);
    }

    public Card blockCard(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber)
            .orElseThrow(() -> new CardNotFoundException(cardNumber));
    }

    public Card rechargeBalance(String cardNumber, double balance) {
        Card card = cardRepository.findByCardNumber(cardNumber)
            .orElseThrow(() -> new CardNotFoundException(cardNumber));
        card.setBalance(card.getBalance() + balance);
        return cardRepository.save(card);
    }

    public double checkBalance(String cardNumber) {
        Card card = cardRepository.findByCardNumber(cardNumber)
            .orElseThrow(() -> new CardNotFoundException(cardNumber));
        return card != null ? card.getBalance() : 0;
    }
}
