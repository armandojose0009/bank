package com.test.webTest.repository;

import com.test.webTest.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCardId(String cardId);

    List<Transaction> findByCardIdAndAnnulled(String cardId, boolean annulled);

    List<Transaction> findByTransactionDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Transaction> findByCardIdAndTransactionDateAfter(String cardId, LocalDateTime date);

    Optional<Transaction> findByIdAndCardId(Long id, String cardId);
}
