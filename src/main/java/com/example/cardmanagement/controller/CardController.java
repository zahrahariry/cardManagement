package com.example.cardmanagement.controller;

import com.example.cardmanagement.model.CardModel;
import com.example.cardmanagement.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/card")
@CrossOrigin
public class CardController {

    @Autowired
    CardService cardService;


    @PostMapping("/save-card")
    public ResponseEntity saveCard (@RequestBody CardModel cardModel) {
        return cardService.saveCard(cardModel);
    }

    @GetMapping("/get-cards-by-person-id/{personId}")
    public ResponseEntity getCardsByPersonId (@PathVariable(name = "personId") Long personId) {
        return cardService.getCardsByPersonId(personId);
    }

    @GetMapping(value = "/get-cards-by-national-id/{nationalId}")
    public ResponseEntity getCardsByNationalId (@PathVariable(name = "nationalId") String nationalId) {
        return cardService.getCardsByNationalId(nationalId);
    }
}
