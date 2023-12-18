package com.test.credibanca.dto;

import com.test.credibanca.entity.Transaction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
@Data
@Getter
@Setter
@EqualsAndHashCode
public class TransactionDTO {
    private Integer transactionId;
    private boolean state;
    private String price;
    private String cardId;
    private Date dateTransaction;

    public TransactionDTO convertEntityToAPI(Transaction transaction) {
        TransactionDTO transactionDTO= new TransactionDTO();
        transactionDTO.transactionId = transaction.getId();
        transactionDTO.state = transaction.getState() == 1;
        transactionDTO.price = formatToDollar(transaction.getPrice());
        transactionDTO.cardId = transaction.getCard().getId();
        transactionDTO.dateTransaction = transaction.getDateTransaction();
        return  transactionDTO;

    }



    public void transformState(Integer state) {
        this.state = state == 1;
    }

    public void transformPrice(Double price) {
        this.price =  formatToDollar(price);
    }



    private String formatToDollar(double amount) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return nf.format(amount);
    }
}
