package com.example.cardmanagement.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Pattern(regexp = "(.*)(\\d{16})(.*)", message = "card number must contains digits !")
    @Size(min = 16, max = 16, message = "card number must have 16 digits !")
    private String cardNumber;

    @Column(name = "ISSUER_CODE")
    @Pattern(regexp = "(.*)(\\d{6})(.*)", message = "issuer code must contains digits !")
    @Size(min = 6, max = 6, message = "issuer code must have 6 digits !")
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
    @Pattern(regexp = "(.*)(\\d{10})(.*)", message = "account number must contains digits !")
    @Size(min = 10, max = 10, message = "account number must have 10 digits !")
    private String accountNumber;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name="person_id", nullable=false)
    private Person person;

}
