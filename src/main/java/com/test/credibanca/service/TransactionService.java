package com.test.credibanca.service;


import com.test.credibanca.dto.TransactionDTO;
import com.test.credibanca.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    TransactionDTO getTransactionById(Integer transactionId);
    TransactionDTO createTransaction(TransactionDTO transaction);
     TransactionDTO cancelTransaction(TransactionDTO transaction);
}