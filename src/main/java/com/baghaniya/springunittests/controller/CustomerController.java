package com.baghaniya.springunittests.controller;


import com.baghaniya.springunittests.model.Customer;
import com.baghaniya.springunittests.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * CustomerController class expose the rest end points for the Customer
 */
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * this method fetch the customer data based on customer id(primary key)
     * @param id this is first paramter to the method (Long primary key)
     * @return this method returns the Customer based on id
     */
    @RequestMapping(value = "/get/customer/{id}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable Long id){
       return customerService.getCustomer(id);
    }

    /**
     * this method saves the new customer in database
     * @param customer this is the first parameter to the method( customer object)
     * @return this method returns the newly created customer object
     */
    @RequestMapping(value = "/save/customer", method = RequestMethod.POST)
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    /**
     * this method updates the already existing customer data in database
     * @param customer this is the first parameter to the method( customer object)
     * @return this method returns the updated customer object
     */
    @RequestMapping(value = "/update/customer/{id}", method = RequestMethod.PUT)
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        return customerService.updateCustomer(id,customer);
    }

    /**
     * this method deletes the already existing customer data in datbase
     * @param id this is the first paramter to the method(id primary key)
     */
    @RequestMapping(value = "/delete/customer", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable Long id){
         customerService.deleteCustomer(id);
    }
}
