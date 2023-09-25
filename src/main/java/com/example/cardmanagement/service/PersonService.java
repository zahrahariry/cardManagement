package com.example.cardmanagement.service;

import com.example.cardmanagement.exception.PersonCurrentlyIsExistsException;
import com.example.cardmanagement.exception.PersonNotFoundException;
import com.example.cardmanagement.mapstruct.PersonMapper;
import com.example.cardmanagement.repository.Card;
import com.example.cardmanagement.repository.Person;
import com.example.cardmanagement.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Log4j2
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper personMapper;


    public Person getPersonById (Long id) {
        Optional<Person> personOpt = personRepository.findAllById(id);
        return personOpt.orElse(null);
    }

    public ResponseEntity getPersonByPersonId (Long id) {
        Person person = getPersonById(id);
        if (person == null) {
            log.error("person with id : {} dose not exists", id);
            throw new PersonNotFoundException();
        }else {
            return new ResponseEntity(personMapper.convertPersonEntityToModel(getPersonById(id)), HttpStatus.OK);
        }
    }

    public Person getPersonByNationalId (String nationalId){
        Optional<Person> personOpt = personRepository.findAllByNationalId(nationalId);
        return personOpt.orElse(null);
    }

    public ResponseEntity getPersonByPersonNationalId (String nationalId) {
        Person person = getPersonByNationalId(nationalId);
        if (person == null) {
            log.error("person with national id : {} dose not exists", nationalId);
            throw new PersonNotFoundException();
        }else {
            return new ResponseEntity(personMapper.convertPersonEntityToModel(person), HttpStatus.OK);
        }
    }

    public void saveCardForPerson (Card card, long personId) {
        Person person = getPersonById(personId);
        if (person == null) {
            log.error("person with id : {} dose not exists !!!", personId);
            throw new PersonNotFoundException();
        }else {
            Set<Card> personCards = person.getCards();
            personCards.add(card);
            person.setCards(personCards);
        }
    }

    public Long savePerson (Person inputPerson) {
        Person person = getPersonByNationalId(inputPerson.getNationalId());
        if (person != null) {
            log.error("person by national id : {} is currently exists !!!", inputPerson.getNationalId());
            throw new PersonCurrentlyIsExistsException();
        }else {
            Person savedPerson = personRepository.saveAndFlush(inputPerson);
            return savedPerson.getId();
        }
    }

    public ResponseEntity getAllPersonsInfos (){
        List<Person> allPersons = personRepository.findAll();
        return new ResponseEntity(personMapper.convertPersonEntityListToModelList(allPersons), HttpStatus.OK);
    }

    public void deleteAllPersonsInDB (){
        personRepository.deleteAll();
    }


}
