package com.test.credibanca.service;


import com.test.credibanca.dto.CardDTO;
import com.test.credibanca.dto.TransactionDTO;
import com.test.credibanca.entity.Card;
import com.test.credibanca.entity.Transaction;
import com.test.credibanca.repository.CardRepository;
import com.test.credibanca.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;


    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, CardRepository cardRepository) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
    }



    @Override
    public TransactionDTO getTransactionById(Integer transactionId) {
        Optional<Transaction>transaction=transactionRepository.findById(transactionId);
        if (transaction.isEmpty()) throw new IllegalArgumentException("the transaction id does not exist.");
        TransactionDTO transactionDTO=new TransactionDTO();
        return  transactionDTO.convertEntityToAPI(transactionRepository.findById(transactionId).get());

    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO transaction) {
        Optional<Card>card=cardRepository.findById(transaction.getCardId());
        if (card.isEmpty())throw new IllegalArgumentException("the card id does not exist.");
        validateCard(transaction,card.get().getState());
        card.get().setBalance(validateTransaction(transaction,card.get()));
        double price=validatePrice(transaction.getPrice());
        Card cardUpdated=cardRepository.save(card.get());
        Transaction transactionEntity=new Transaction(null,1,price,new Date(),cardUpdated);
        return  new TransactionDTO().convertEntityToAPI(transactionRepository.save(transactionEntity));
    }

    @Override
    public TransactionDTO cancelTransaction(TransactionDTO transaction) {
        Optional<Card>card=cardRepository.findById(transaction.getCardId());
        if (card.isEmpty())throw new IllegalArgumentException("the card id does not exist.");
        Optional<Transaction>transactionEntity=transactionRepository.findById(transaction.getTransactionId());
        if (transactionEntity.isEmpty() || !transactionEntity.get().getCard().getId().equals(card.get().getId()) ){
            throw new IllegalArgumentException("the transaction id does not exist or does not match the id card.");}
        card.get().setBalance(validateCancellationDate(transactionEntity.get(),card.get()));
       System.out.println("este es el nuevo balance"+ card.get().getBalance());
        cardRepository.save(card.get());
        transactionEntity.get().setState(0);
        return  new TransactionDTO().convertEntityToAPI(transactionRepository.save(transactionEntity.get()));
    }




    private void validateCard(TransactionDTO transaction,Integer activated) {
        if (activated != 1) throw new IllegalArgumentException("the card is blocked,please unlock it first ");
    }

    private Double validateTransaction(TransactionDTO transaction,Card card) {

        if (card.getBalance()<Double.parseDouble(transaction.getPrice())) {
            throw new IllegalArgumentException("You do not have enough balance on the card to make the transaction, recharge it first");
        }
        else{
            return (card.getBalance()-Double.parseDouble(transaction.getPrice()));
        }


    }
    private double validatePrice(String price) throws IllegalArgumentException {
        if (price == null || price.isEmpty()|| price.equals("0")) throw new IllegalArgumentException("price is required");
        if (!Pattern.matches("[0-9]+", price)) throw new IllegalArgumentException("price should contain only numeric digits.");
        return Double.parseDouble(price);
    }

    private Double validateCancellationDate(Transaction transaction,Card card) {
        long dif = Math.abs(new Date().getTime() - transaction.getDateTransaction().getTime());
        long convertionHours = dif / (60 * 60 * 1000);

        if (convertionHours > 24) {
            throw new IllegalStateException("Transaction cannot be canceled after 24 hours");
        }
        else if (transaction.getState()!=1) {
            throw new IllegalStateException("the transaction has already been canceled");
        }
        else{
            return (card.getBalance()+transaction.getPrice());
        }


    }

}