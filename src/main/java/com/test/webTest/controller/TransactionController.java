package com.test.webTest.controller;

import com.test.webTest.model.Transaction;
import com.test.webTest.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/purchase")
    public Transaction createPurchaseTransaction(@RequestBody Map<String, String> request) {
        return transactionService.createPurchaseTransaction(request.get("cardId"), Double.parseDouble(request.get("price")));
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransaction(@PathVariable Long transactionId) {
        return transactionService.getTransaction(transactionId);
    }

    @PostMapping("/anulation")
    public Transaction annulTransaction(@RequestBody Map<String, String> request) {
        return transactionService.annulTransaction(request.get("cardId"), Long.parseLong(request.get("transactionId")));
    }
}
