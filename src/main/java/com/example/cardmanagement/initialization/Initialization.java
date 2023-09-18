package com.example.cardmanagement.initialization;

import com.example.cardmanagement.repository.CardRepository;
import com.example.cardmanagement.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Component
@Log4j2
public class Initialization {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    PersonRepository personRepository;

    @PostConstruct
    private void initDB() {
        log.info("db initialization process has been started !");
        String inputFile = "/home/hariry/JavaWorkspace/cardManagement/src/main/resources/initialData.txt";
        File file = new File(inputFile);
        try (FileReader fr = new FileReader(file)){
            BufferedReader br = new BufferedReader(fr);
            String eachLine = "";
            while((eachLine = br.readLine()) != null){
            }
        }catch (Exception e) {
            log.error("an error has occurred in DB initialization !");
        }
        log.info("db has been initialized successfully !");
    }
}
