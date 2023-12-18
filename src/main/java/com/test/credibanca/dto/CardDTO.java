package com.test.credibanca.dto;

import com.test.credibanca.entity.Card;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

@Data
@Getter
@Setter
public class CardDTO {
    public String cardId;
    private Date expirationDate;
    private boolean state;
    private String balance;
    private String name;
    private String lastName;


    public CardDTO convertEntityToAPI(Card card) {
        CardDTO cardDtoTemp= new CardDTO();
        cardDtoTemp.cardId = card.getId();
        cardDtoTemp.expirationDate = card.getExpirationDate();
        cardDtoTemp.state = card.getState() == 1;
        cardDtoTemp.balance = formatToDollar(card.getBalance());
        cardDtoTemp.name = card.getUser().getName();
        cardDtoTemp.lastName = card.getUser().getLastName();
        return cardDtoTemp;

    }
    public CardDTO( ) {

    }





    private String formatToDollar(double amount) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return nf.format(amount);
    }



    public void setCardId(String cardId) {
        this.cardId = cardId;
    }



    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }


    public void transformState(Integer state) {
        this.state = state == 1;
    }



    public void transformBalance(Double balance) {
        this.balance = formatToDollar(balance);

    }



    public void setName(String name) {
        this.name = name;
    }



    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}