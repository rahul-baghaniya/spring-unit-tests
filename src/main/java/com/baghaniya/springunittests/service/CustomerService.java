package com.baghaniya.springunittests.service;

import com.baghaniya.springunittests.model.Customer;
import com.baghaniya.springunittests.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomer(Long id){
        Customer result = customerRepository.getOne(id);
        if(result == null)
            throw new EntityNotFoundException();
       return result;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    public Customer updateCustomer(Long id,Customer customer) {
        Customer updatedCustomer = customerRepository.getOne(id);
        if(updatedCustomer == null)
            throw new EntityNotFoundException();
        updatedCustomer.setName(customer.getName());
        return customerRepository.save(updatedCustomer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.getOne(id);
        if(customer == null)
            throw  new EntityNotFoundException();
         customerRepository.delete(customer);
    }
}
