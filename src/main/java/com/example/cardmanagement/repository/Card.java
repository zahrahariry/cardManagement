package com.example.cardmanagement.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "CARD")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @Column(name = "ISSUER_CODE")
    private String issuerCode;

    @Column(name = "CARD_TYPE")
    private CardType cardType;

    @Column(name = "ISSUER_NAME")
    private String issuerName;

    @Column(name = "EXPIRATION_MONTH")
    private int expirationMonth;

    @Column(name = "EXPIRATION_YEAR")
    private int expirationYear;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name="person_id", nullable=false)
    private Person person;

}
