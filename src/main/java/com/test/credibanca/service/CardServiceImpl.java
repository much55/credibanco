package com.test.credibanca.service;

import com.test.credibanca.dto.CardDTO;
import com.test.credibanca.entity.Card;
import com.test.credibanca.entity.User;
import com.test.credibanca.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserService userService;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, UserService userService) {
        this.cardRepository = cardRepository;
        this.userService = userService;
    }



    @Override
    public CardDTO getCardById(String cardId) {
        Optional<Card>card=cardRepository.findById(cardId);
        if (card.isEmpty()) throw new IllegalArgumentException("the card id does not exist.");
        CardDTO cardDTO=new CardDTO();
        return  cardDTO.convertEntityToAPI(cardRepository.findById(cardId).get());
    }

    @Override
    public CardDTO createCard(String cardId, User user) {
        validateCardNumber(cardId);
        User us= userService.createUser(user);
        Card card=new Card(generateNumbers(cardId),addThreeYearsToDate(new Date()),0,0.0,us);
        CardDTO cardDTO=new CardDTO();
        return  cardDTO.convertEntityToAPI(cardRepository.save(card));

    }

    @Override
    public CardDTO activatedCard(String card) {
        Optional<Card> cardUpdate=cardRepository.findById(card);
        if (cardUpdate.isEmpty()) {
            throw new IllegalArgumentException("the card id does not exist.");}
        else{
            cardUpdate.get().setState(1);
            CardDTO cardDTO=new CardDTO();
            return  cardDTO.convertEntityToAPI(cardRepository.save(cardUpdate.get()));
        }

    }
    @Override
    public CardDTO RechargeBalance(String card,String balance) {
        Optional<Card> cardUpdate=cardRepository.findById(card);
        double mount=validateBalance(balance);
        if (cardUpdate.isEmpty()) {
            throw new IllegalArgumentException("the card id does not exist.");
        }
        else{

            cardUpdate.get().setBalance(cardUpdate.get().getBalance()+ mount);
            CardDTO cardDTO=new CardDTO();
            return  cardDTO.convertEntityToAPI(cardRepository.save(cardUpdate.get()));
        }

    }

    @Override
    public String deleteCard(String card) {
        Optional<Card> cardUpdate=cardRepository.findById(card);
        if (cardUpdate.isEmpty()) {
            throw new IllegalArgumentException("the card id does not exist.");
        }
        else{
            cardUpdate.get().setState(0);
            cardRepository.save(cardUpdate.get());
            return "the card has been blocked";

        }
    }


    private void validateCardNumber(String cardNumber) throws IllegalArgumentException {
        if (!Pattern.matches("[0-9]+", cardNumber)) {
            throw new IllegalArgumentException("The card number should contain only numeric digits.");
        } else if (cardNumber.length() != 6) {
            throw new IllegalArgumentException("The card number should have exactly 6 digits.");
        }
    }
    private double validateBalance(String balance) throws IllegalArgumentException {
        if (balance == null || balance.isEmpty()) {
            throw new IllegalArgumentException("Balance is required");
        }
        if (!Pattern.matches("[0-9]+", balance)) {
            throw new IllegalArgumentException("Balance should contain only numeric digits.");
        }
        return Double.parseDouble(balance);
    }

    private Date addThreeYearsToDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        LocalDate localDate = LocalDate.parse(dateStr);
        localDate = localDate.plusYears(3);

        return java.sql.Date.valueOf(localDate);
    }



    public String generateNumbers(String inputNumber) {

        if (inputNumber.length() != 6) {
            throw new IllegalArgumentException("The card number should have exactly 6 digits.");
        }
        StringBuilder concatenatedNumbers = new StringBuilder(inputNumber);
        for (int i = 0; i < 10; i++) {
            concatenatedNumbers.append((int) (Math.random() * 10));
        }

        return concatenatedNumbers.toString();
    }


    private void validateCard(Card card) {
        if (card.getExpirationDate() == null) {
            throw new IllegalArgumentException("Expiration date is required");
        }

        if (card.getState() == null) {
            throw new IllegalArgumentException("State is required");
        }

        if (card.getBalance() == null) {
            throw new IllegalArgumentException("Balance is required");
        }


    }

}