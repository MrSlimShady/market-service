package com.stockmarket.marketservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockmarket.marketservice.MarketServiceApplication;
import com.stockmarket.marketservice.entity.UserEntity;
import org.apache.poi.sl.usermodel.ObjectMetaData;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MarketServiceApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class ControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void registerUserTest() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        UserEntity userEntity= new UserEntity("mock1","password","ROLE_USER","mock1@mail","9876543210");

        String json =objectMapper.writeValueAsString(userEntity);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void signInUserTest() throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"admin\", \"password\": \"12345678\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void getAllCompaniesTest() throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/company/all").accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    @Test
    public void getAllExchangesTest() throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/exchange/all").accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    @Test
    public void getAllIPOsTest() throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/ipo/all").accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    @Test
    public void getSectorTest() throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/sector/all").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void confirmUserTest() throws Exception{
       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/confirm/53").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void addExchangeTest() throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/exchange/add").contentType(MediaType.APPLICATION_JSON)
                .content("{\"stockExchange\": \"mockexchange\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    @Test
    public void addIPOTest() throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/ipo/add").contentType(MediaType.APPLICATION_JSON)
                .content("{\"companyName\": \"mockCompany\", \"stockExchange\": \"mockExchange\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void addSectorTest() throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/exchange/add").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"mockSector\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }




}
