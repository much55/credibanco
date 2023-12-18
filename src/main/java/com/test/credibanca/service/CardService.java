package com.test.credibanca.service;


import com.test.credibanca.dto.CardDTO;
import com.test.credibanca.entity.Card;
import com.test.credibanca.entity.User;

import java.util.List;

public interface CardService {

    CardDTO getCardById(String cardId);
    CardDTO createCard(String cardId, User user);
    CardDTO activatedCard(String card);
    String deleteCard(String cardId);
     CardDTO RechargeBalance(String card,String balance);

}