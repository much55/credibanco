package com.test.credibanca.controller;

import com.test.credibanca.controller.TransactionController;
import com.test.credibanca.dto.TransactionDTO;
import com.test.credibanca.entity.Card;
import com.test.credibanca.entity.Transaction;
import com.test.credibanca.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionControllerTest {

    private TransactionController transactionController;
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = mock(TransactionService.class);
        transactionController = new TransactionController(transactionService);
    }

    private TransactionDTO createMockTransactionDTO() {
        TransactionDTO mockTransactionDTO = new TransactionDTO();
        mockTransactionDTO.setTransactionId(22);
        mockTransactionDTO.setDateTransaction(new Date());
        mockTransactionDTO.transformPrice(100.0);
        mockTransactionDTO.setState(true);
        mockTransactionDTO.setCardId("1234541639223000");
        return mockTransactionDTO;
    }

    private Transaction createMockTransactionEntity() {
        Transaction transaction = new Transaction();
        Card card= new Card();
        card.setId("1234541639223000");
        transaction.setCard(card);
        transaction.setId(22);
        transaction.setDateTransaction(new Date());
        transaction.setPrice(100.0);
        transaction.setState(1);
        return transaction;
    }

    @Test
    void testGetTransactionById() {
        TransactionDTO mockTransactionDTO = createMockTransactionDTO();
        when(transactionService.getTransactionById(22)).thenReturn(mockTransactionDTO);
        ResponseEntity<?> response = transactionController.getTransactionById(22);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(22, ((TransactionDTO) response.getBody()).getTransactionId());
    }

    @Test
    void testCreateTransaction() {
        TransactionDTO mockTransactionDTO = createMockTransactionDTO();
        Transaction transaction = createMockTransactionEntity();
        when(transactionService.createTransaction(mockTransactionDTO)).thenReturn(mockTransactionDTO);
        ResponseEntity<?> response = transactionController.createTransaction(mockTransactionDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(22, ((TransactionDTO) response.getBody()).getTransactionId());
    }

    @Test
    void testCancelTransaction() {
        TransactionDTO mockTransactionDTO = createMockTransactionDTO();
        Transaction transaction = createMockTransactionEntity();
        when(transactionService.cancelTransaction(mockTransactionDTO)).thenReturn(mockTransactionDTO);
        ResponseEntity<?> response = transactionController.cancelTransaction(mockTransactionDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(22, ((TransactionDTO) response.getBody()).getTransactionId());
    }
}