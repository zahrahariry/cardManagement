package com.example.cardmanagement.PersonController;

import com.example.cardmanagement.model.PersonModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPersonByPersonNationalId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/get-person-by-national-id/{nationalId}", "0082222222"))
                .andExpect(status().isOk()).andReturn();
        ObjectMapper mapper = new ObjectMapper();
        PersonModel personModel = mapper.readValue(mvcResult.getResponse().getContentAsString(), PersonModel.class);
        Assert.assertEquals("name2", personModel.getName());

    }

    @Test
    public void testGetAllPersonsInfos() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/get-all-persons"))
                .andExpect(status().isOk()).andReturn();
        ObjectMapper mapper = new ObjectMapper();
        List<PersonModel> personModels = Arrays.asList(mapper.readValue(mvcResult.getResponse().getContentAsString(), PersonModel[].class));
        Assert.assertEquals("0083333333", personModels.get(2).getNationalId());

    }
}
