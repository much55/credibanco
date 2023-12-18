package com.test.credibanca.controller;


import com.test.credibanca.dto.CardDTO;
import com.test.credibanca.entity.User;
import com.test.credibanca.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    private  CardDTO cardDTO;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;

    }



    @GetMapping("/balance/{cardId}")
    public ResponseEntity<?> getCardById(@PathVariable String cardId) {
        try{
            return ResponseEntity.ok(cardService.getCardById(cardId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); //
        }

    }
    @PostMapping("/{productId}/number")
    public ResponseEntity<?> createCard(@PathVariable String productId, @RequestBody User user) {
       try{
            return ResponseEntity.ok(cardService.createCard(productId,user));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); //
        }
    }

    @PostMapping("/enroll")
    public ResponseEntity<?> activeCard(@RequestBody CardDTO card) {
        try{
            return ResponseEntity.ok(cardService.activatedCard(card.getCardId()));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); //
        }
    }

    @PostMapping("/balance")
    public ResponseEntity<?> RechargeBalance(@RequestBody CardDTO card) {
        try{
            return ResponseEntity.ok(cardService.RechargeBalance(card.getCardId(),card.getBalance()));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); //
        }
    }


    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable String cardId) {
        try{
            return ResponseEntity.ok(cardService.deleteCard(cardId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); //
        }

    }

}