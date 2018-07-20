package com.baghaniya.springunittests.service;

import com.baghaniya.springunittests.model.Customer;
import com.baghaniya.springunittests.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;


    @Test
    public void testGetCustomer() throws Exception{
        Customer returned = new Customer(1L, "rahul");
        //stub the data
        Mockito.when(customerRepository.getOne(1L)).thenReturn(returned);

        Customer result = customerService.getCustomer(1L);
        Assert.assertEquals("rahul", result.getName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetCustomer_notFound(){
       doThrow(new EntityNotFoundException()).when(customerRepository).getOne(1L);
        customerService.getCustomer(1L);
    }


    @Test
    public void testSaveCustomer() throws Exception{
        Customer input1 = new Customer(1L, "anjali");
        Customer input2 = new Customer(2L, "rahul");

        Customer returned1 = new Customer(1L, "anjali");
        Customer returned2 = new Customer(2L, "rahul");

        //stub the data
        Mockito.when(customerRepository.save(input1)).thenReturn(returned1);
        Mockito.when(customerRepository.save(input2)).thenReturn(returned2);

        //actual method call
        Customer result1 = customerService.saveCustomer(input1);
        Customer result2 = customerService.saveCustomer(input2);

        Assert.assertEquals("anjali", result1.getName());
        Assert.assertEquals("rahul", result2.getName());
    }

    @Test
    public void testUpdateCustomer(){
        Customer input = new Customer(1L, "anjali");

        Customer returned = new Customer(1L, "anjali_updated");

        //stub the data
        Mockito.when(customerRepository.getOne(1L)).thenReturn(input);
        Mockito.when(customerRepository.save(input)).thenReturn(returned);

        //actual method call
        Customer result = customerService.updateCustomer(1L,input);

        Assert.assertEquals("anjali_updated", result.getName());
    }


    @Test(expected = EntityNotFoundException.class)
    public void testUpdateCustomer_notfound(){
        doThrow(new EntityNotFoundException()).when(customerRepository).getOne(1L);
        Customer input = new Customer(1L, "anjali");
        customerService.updateCustomer(1L,input);
    }


    @Test
    public void testDeleteCustomer() throws Exception{
        Customer input = new Customer(1L, "anjali");

        //stub the data
        Mockito.when(customerRepository.getOne(1L)).thenReturn(input);
        doNothing().when(customerRepository).delete(input);

        customerService.deleteCustomer(1L);
    }

}
