package com.test.webTest.service;

import com.test.webTest.model.Card;
import com.test.webTest.model.Transaction;
import com.test.webTest.repository.CardRepository;
import com.test.webTest.repository.TransactionRepository;
import com.test.webTest.exceptions.CardNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    public Transaction createPurchaseTransaction(String cardId, double amount) {
        Card card = cardRepository.findByCardNumberAndActiveAndBlockedFalse(cardId,true)
                                                    .orElseThrow(() -> new CardNotFoundException(cardId));
        if (card.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        if (card.getExpiryDate().isBefore(ChronoLocalDate.from(LocalDateTime.now()))) {
            throw new RuntimeException("Card expired");
        }

        Transaction transaction = new Transaction();
        transaction.setCardId(cardId);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAnnulled(false);

        card.setBalance(card.getBalance() - amount);
        cardRepository.save(card);
        return transactionRepository.save(transaction);
    }

    public Transaction annulTransaction(String cardId, Long transactionId) {
        Optional<Transaction> transactionOptional = transactionRepository.findByIdAndCardId(transactionId, cardId);
        if (transactionOptional.isEmpty()) {
            throw new RuntimeException("Transaction not found");
        }
        Transaction transaction = transactionOptional.get();
        if (transaction.isAnnulled()) {
            throw new RuntimeException("Transaction already annulled");
        }
        if (transaction.getTransactionDate().isBefore(LocalDateTime.now().minusHours(24))) {
            throw new RuntimeException("Transaction too old to annul");
        }

        transaction.setAnnulled(true);
        Optional<Card> cardOptional = cardRepository.findByCardNumber(cardId);
        if (cardOptional.isEmpty()) {
            throw new RuntimeException("Card not found");
        }
        Card card = cardOptional.get();
        card.setBalance(card.getBalance() + transaction.getAmount());
        cardRepository.save(card);
        return transactionRepository.save(transaction);
    }

    public Transaction getTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public List<Transaction> getTransactionsByCardId(String cardId) {
        return transactionRepository.findByCardId(cardId);
    }
}
