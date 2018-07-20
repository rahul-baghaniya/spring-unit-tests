package com.baghaniya.springunittests.controller;

import com.baghaniya.springunittests.model.Customer;
import com.baghaniya.springunittests.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by RAHUL on Jul, 2018
 */
@RunWith(SpringRunner.class)
public class CustomerControllerTest {
    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;


     @Test
    public void testGetCustomer(){
         Customer returned = new Customer(1L,"anjali");
         String expected = "{id:1,name:anjali}";
         //mock the data
         Mockito.when(customerService.getCustomer(1L)).thenReturn(returned);

         //original method call
         Customer result = customerController.getCustomer(1L);

         Assert.assertEquals(result.getName(),"anjali");
     }


     @Test
    public void  testSaveCustomer() throws Exception{
         Customer input = new Customer(1L, "anjali");
         Customer returned = new Customer(1L,"anjali");
         //stub the data
         Mockito.when(customerService.saveCustomer(input)).thenReturn(returned);

         //original method call
         Customer result = customerController.saveCustomer(input);

         Assert.assertEquals(result.getName(),"anjali");
     }


     @Test
    public void testUpdateCustomer() throws Exception{
         Customer input = new Customer(1L, "rahul");
         Customer returned = new Customer(1L,"rahul_updated");
         //stub the data
         Mockito.when(customerService.updateCustomer(1L,input)).thenReturn(returned);

         //original method call
         Customer result = customerController.updateCustomer(1L,input);

         Assert.assertEquals(result.getName(), "rahul_updated");
     }

}
