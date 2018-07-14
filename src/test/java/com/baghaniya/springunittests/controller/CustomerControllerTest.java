package com.baghaniya.springunittests.controller;

import com.baghaniya.springunittests.model.Customer;
import com.baghaniya.springunittests.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class,secure = false)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCustomer() throws Exception{
        Customer returned = new Customer(1L,"rahul");
        String expected = "{id:1,name:rahul}";
        //mock the data
        Mockito.when(customerService.getCustomer(1L)).thenReturn(returned);

        //controller url preparation
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/get/customer/1")
                .accept(MediaType.APPLICATION_JSON);

        //original controller method url calling from method "getCustomer()"
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        //check expected result with mvcResult
        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }

   @Test
    public void testSaveCustomer() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Customer input = new Customer(1L, "anjali");
        String input_json = mapper.writeValueAsString(input);
        Customer returned = new Customer(1L,"anjali");
        String expected = "{id:1,name:anjali}";
        //stub the data
        Mockito.when(customerService.saveCustomer(input)).thenReturn(returned);

        // //controller url preparation
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/save/customer")
                .accept(MediaType.APPLICATION_JSON).content(input_json)
                .contentType(MediaType.APPLICATION_JSON);

        //original controller method url calling from method "Customer()"
        MvcResult result = mockMvc.perform(requestBuilder)
                .andReturn();

        String result1= result.getResponse().getContentAsString();

        //check expected result with mvcResult
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    }

    @Test
    public void testUpdateCustomer() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Customer input = new Customer(1L, "rahul");
        String input_json = mapper.writeValueAsString(input);
        Customer returned = new Customer(1L,"rahul_updated");
        String expected = "{id:1,name:rahul_updated}";
        //stub the data
        Mockito.when(customerService.updateCustomer(1L,input)).thenReturn(returned);

        //controller url preparation
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
                "/update/customer/1")
                .accept(MediaType.APPLICATION_JSON).content(input_json)
                .contentType(MediaType.APPLICATION_JSON);


        //original controller method url calling from method "getCustomer()"
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String result  = mvcResult.getResponse().getContentAsString();
        //check expected result with mvcResult
        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }

  /* @Test
    public void testDeleteCustomer(){
       Mockito.when(customerService.deleteCustomer(1L));

   }*/


}
