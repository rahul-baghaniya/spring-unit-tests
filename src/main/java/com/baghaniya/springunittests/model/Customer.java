package com.baghaniya.springunittests.model;

import javax.persistence.*;

/**
 * this is the entity class for Customer
 * this class will create the table "customers" in database
 */
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private Long id;
    private String name;

    public Customer() {
    }

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
