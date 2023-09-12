package com.example.cardmanagement.model;

import com.example.cardmanagement.repository.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonModel {

    private Long id;
    private String nationalId;
    private String phoneNumber;
    private String address;
    private String name;
    private String family;
    private Set<Card> cards;
}
