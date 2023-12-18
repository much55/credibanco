package com.test.credibanca.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Card {


    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "state")
    private Integer state;

    @Column(name = "balance")
    private Double balance;

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;

}