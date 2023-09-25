package com.example.cardmanagement.initialization;

import com.example.cardmanagement.repository.*;
import com.example.cardmanagement.service.CardService;
import com.example.cardmanagement.service.PersonService;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Component
@Log4j2
public class Initialization {

    @Autowired
    PersonService personService;

    @Autowired
    CardService cardService;

    @Value("${initialData.file.path}")
    private String initialDataPath;

    @PostConstruct
    private void initDB() {
        log.info("clearing database has been started !!!");
        cardService.deleteAllCardsInDB();
        personService.deleteAllPersonsInDB();
        log.info("clearing the database has been ended !!!");
        log.info("db initialization process has been started !");
        File file = new File(initialDataPath);
        try (FileReader fr = new FileReader(file)){
            BufferedReader br = new BufferedReader(fr);
            String eachLine = "";
            while((eachLine = br.readLine()) != null){
                String[] eachLineInfos =  eachLine.split("&");
                String[] infos = eachLineInfos[0].split(",");
                Person person = Person.builder().nationalId(infos[4]).phoneNumber(infos[3]).address(infos[2]).name(infos[0]).family(infos[1]).build();
                person.setId(personService.savePerson(person));
                for (int counter=1; counter< eachLineInfos.length; ++counter) {
                    String[] cardInfos = eachLineInfos[counter].split(",");
                    CardType cardType = CardType.CASH;
                    if (cardInfos[2].equals("cash")){
                        cardType = CardType.CASH;
                    }else if (cardInfos[2].equals("credit")){
                        cardType = CardType.CREDIT;
                    }
                    Boolean isActive = false;
                    if (cardInfos[7].equals("1")){
                        isActive = true;
                    }
                    Card card = Card.builder().cardNumber(cardInfos[0]).issuerCode(cardInfos[1]).cardType(cardType).issuerName(cardInfos[3]).expirationMonth(Integer.parseInt(cardInfos[4]))
                            .expirationYear(Integer.parseInt(cardInfos[5])).accountNumber(cardInfos[6]).isActive(isActive).person(person).build();
                    card.setId(cardService.saveCard(card));
                }
            }
        }catch (Exception e) {
            log.error("an error has occurred in DB initialization !");
        }
        log.info("db has been initialized successfully !");
    }
}
