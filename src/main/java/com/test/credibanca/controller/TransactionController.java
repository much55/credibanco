package com.test.credibanca.controller;
import com.test.credibanca.dto.TransactionDTO;
import com.test.credibanca.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }



    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransactionById(@PathVariable Integer transactionId) {
        try{
            return ResponseEntity.ok(transactionService.getTransactionById(transactionId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @PostMapping("/purchase")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDTO transaction) {
        try{
            return ResponseEntity.ok(transactionService.createTransaction(transaction));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); //
        }
    }
    @PostMapping("/anulation")
    public ResponseEntity<?> cancelTransaction(@RequestBody TransactionDTO transaction) {
        try{
            return ResponseEntity.ok(transactionService.cancelTransaction(transaction));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); //
        }
    }


}