package com.test.webTest.controller;

import com.test.webTest.model.Card;
import com.test.webTest.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/{productId}/number")
    public String generateCardNumber(@PathVariable String productId) {
        return cardService.generateCardNumber(productId);
    }

    @PostMapping("/enroll")
    public Card enrollCard(@RequestBody Map<String, String> request) {
        return cardService.enrollCard(request.get("cardId"));
    }

    @DeleteMapping("/{cardId}")
    public Card blockCard(@PathVariable String cardId) {
        return cardService.blockCard(cardId);
    }

    @PostMapping("/balance")
    public Card rechargeBalance(@RequestBody Map<String, String> request) {
        return cardService.rechargeBalance(request.get("cardId"), Double.parseDouble(request.get("balance")));
    }

    @GetMapping("/balance/{cardId}")
    public double checkBalance(@PathVariable String cardId) {
        return cardService.checkBalance(cardId);
    }
}
