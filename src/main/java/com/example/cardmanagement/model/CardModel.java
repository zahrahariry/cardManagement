package com.example.cardmanagement.model;

import com.example.cardmanagement.repository.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardModel {

    private Long id;
    private String cardNumber;
    private String issuerCode;
    private CardType cardType;
    private String issuerName;
    private int expirationMonth;
    private int expirationYear;
    private String accountNumber;
    private Boolean isActive;
    private Long personId;
}
