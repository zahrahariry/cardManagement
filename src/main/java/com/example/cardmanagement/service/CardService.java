package com.example.cardmanagement.service;

import com.example.cardmanagement.exception.CardCurrentlyIsExistsException;
import com.example.cardmanagement.exception.PersonNotFoundException;
import com.example.cardmanagement.mapstruct.CardMapper;
import com.example.cardmanagement.model.CardModel;
import com.example.cardmanagement.repository.Card;
import com.example.cardmanagement.repository.CardRepository;
import com.example.cardmanagement.repository.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardMapper cardMapper;

    @Autowired
    PersonService personService;

    public void saveCardForPerson (Card card, Long personId) {
        Person person = personService.getPersonById(personId);
        if (person == null) {
            log.error("person with id : {} dose not exists !!!", personId);
            throw new PersonNotFoundException();
        }else {
            card.setPerson(person);
            cardRepository.saveAndFlush(card);
        }
    }

    public void saveCardForPerson (CardModel cardModel) {
        Card card = cardMapper.convertCardModelToEntity(cardModel);
        card.setPerson(personService.getPersonById(cardModel.getPersonId()));
        cardRepository.save(card);
        log.info("card has been saved for person id : {}", cardModel.getPersonId());
    }

    public Long saveCard (Card card) {
        return cardRepository.save(card).getId();
    }

    public ResponseEntity saveCard (CardModel cardModel) {
        Optional<Card> cardOpt = cardRepository.findCardByCardNumber(cardModel.getCardNumber());
        if (cardOpt.isPresent()) {
            log.error("card with card number : {} is currently exists", cardModel.getCardNumber());
            throw new CardCurrentlyIsExistsException();
        }else {
            if (personService.getPersonById(cardModel.getPersonId()) == null) {
                log.error("person with id : {} not found", cardModel.getPersonId());
                throw new PersonNotFoundException();
            }else {
                saveCardForPerson(cardModel);
                log.info("card with number : {} has been saved", cardModel.getCardNumber());
                return new ResponseEntity(HttpStatus.OK);
            }
        }
    }

    public ResponseEntity getCardsByPersonId (Long personId) {
        List<Card> cards = cardRepository.findCardsByPersonId(personId);
        List<CardModel> cardModels = new ArrayList<>();
        cards.forEach(card -> {
            CardModel cardModel = cardMapper.convertCardEntityToModel(card);
            cardModel.setPersonId(card.getPerson().getId());
            cardModels.add(cardModel);
        });
        return new ResponseEntity(cardModels, HttpStatus.OK);
    }

    public ResponseEntity getCardsByNationalId (String nationalId) {
        Person person = personService.getPersonByNationalId(nationalId);
        if (person == null) {
            log.info("person with national id : {} not found", nationalId);
            throw new PersonNotFoundException();
        }else {
            List<Card> allCards = cardRepository.findCardsByPerson(person);
            List<CardModel> cardModels = new ArrayList<>();
            allCards.forEach(card -> {
                CardModel cardModel = cardMapper.convertCardEntityToModel(card);
                cardModel.setPersonId(card.getPerson().getId());
                cardModels.add(cardModel);
            });
            return new ResponseEntity(cardModels, HttpStatus.OK);
        }
    }

    public ResponseEntity getAllCardsInfos () {
        List<Card> allCards = cardRepository.findAll();
        List<CardModel> cardModels = new ArrayList<>();
        allCards.forEach(card -> {
            CardModel cardModel = cardMapper.convertCardEntityToModel(card);
            cardModel.setPersonId(card.getPerson().getId());
            cardModels.add(cardModel);
        });
        return new ResponseEntity(cardModels, HttpStatus.OK);
    }

    public void deleteAllCardsInDB (){
        cardRepository.deleteAll();
    }
}
