package com.example.cardmanagement.controller;

import com.example.cardmanagement.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/person")
@CrossOrigin
public class PersonController {

    @Autowired
    PersonService personService;


    @GetMapping(value = "/get-person-by-id/{id}")
    public ResponseEntity getPersonByPersonId (@PathVariable(name = "id") Long id) {
        return personService.getPersonByPersonId(id);
    }

    @GetMapping(value = "/get-person-by-national-id/{nationalId}")
    public ResponseEntity getPersonByPersonNationalId (@PathVariable(name = "nationalId") String nationalId) {
        return personService.getPersonByPersonNationalId(nationalId);
    }

    @GetMapping(value = "/get-all-persons")
    public ResponseEntity getAllPersonsInfos (){
        return personService.getAllPersonsInfos();
    }
}
