package com.test.webTest.repository;

import com.test.webTest.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardNumber(String cardNumber);
    List<Card> findByCardNumberStartingWith(String productId);
    List<Card> findByActive(boolean active);
    List<Card> findByBlocked(boolean blocked);
    Optional<Card> findByCardNumberAndActiveAndBlockedFalse(String cardNumber, boolean active);
}