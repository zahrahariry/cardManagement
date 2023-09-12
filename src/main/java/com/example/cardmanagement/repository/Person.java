package com.example.cardmanagement.repository;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "PERSON")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NATIONAL_ID")
    private String nationalId;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "NAME")
    private String name;

    @Column(name = "FAMILY")
    private String family;

    @OneToMany(mappedBy = "person")
    private Set<Card> cards;

}
