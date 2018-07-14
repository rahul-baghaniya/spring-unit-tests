package com.baghaniya.springunittests.repository;

import com.baghaniya.springunittests.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * this interface will perform CRUD operations on database for table "customers"
 */
@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long> {

}
