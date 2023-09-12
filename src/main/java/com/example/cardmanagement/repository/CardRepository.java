package com.example.cardmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends CrudRepository<Card, Long>, JpaRepository<Card, Long> {

    Optional<Card> findCardById (Long id);

    Optional<Card> findCardByCardNumber (String cardNumber);

    Optional<Card> findCardByIsActive (Boolean isActive);

    List<Card> findCardsByPerson (Person person);

    List<Card> findCardsByPersonId (Long personId);


}
