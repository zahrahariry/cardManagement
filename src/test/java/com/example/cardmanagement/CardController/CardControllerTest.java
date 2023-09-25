package com.example.cardmanagement.CardController;

import com.example.cardmanagement.model.CardModel;
import com.example.cardmanagement.model.PersonModel;
import com.example.cardmanagement.repository.CardType;
import com.example.cardmanagement.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonService personService;
    @Test
    public void testSaveCard() throws Exception {
        List<PersonModel> personModels = (List<PersonModel>) personService.getAllPersonsInfos().getBody();
        CardModel testCard = CardModel.builder().id(1l).cardNumber("9999999999999999").issuerName("issuer9").issuerCode("999999")
                .cardType(CardType.CREDIT).expirationMonth(9).expirationYear(1409).accountNumber("9999999999").isActive(true).personId(personModels.get(0).getId()).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(testCard);
        MvcResult mvcResult = mockMvc.perform(post("/save-card").contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    public void testGetCardsByNationalId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/get-cards-by-national-id/{nationalId}", "0082222222"))
                .andExpect(status().isOk()).andReturn();
        ObjectMapper mapper = new ObjectMapper();
        List<CardModel> cardModels = Arrays.asList(mapper.readValue(mvcResult.getResponse().getContentAsString(), CardModel[].class));
        Assert.assertEquals("2222222222222222", cardModels.get(1).getCardNumber());

    }

    @Test
    public void testGetAllCardInfos() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/get-all-cards"))
                .andExpect(status().isOk()).andReturn();
        ObjectMapper mapper = new ObjectMapper();
        List<CardModel> cardModels = Arrays.asList(mapper.readValue(mvcResult.getResponse().getContentAsString(), CardModel[].class));
        Assert.assertEquals(CardType.CASH, cardModels.get(0).getCardType());
    }
}
