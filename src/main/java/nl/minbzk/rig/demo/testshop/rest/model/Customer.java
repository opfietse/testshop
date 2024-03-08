package nl.minbzk.rig.demo.testshop.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nl.minbzk.rig.demo.testshop.jpa.model.Order;

import java.util.List;

public class Customer {
    private Long id;
    private String name;
    private String customerNumber;
    private String emailAddress;

    private List<Order> aanvragen;

    public Customer id(Long id) {
        this.id = id;
        return this;
    }

    public Customer name(String name) {
        this.name = name;
        return this;
    }

    public Customer customerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
        return this;
    }

    public Customer emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public List<Order> getAanvragen() {
        return aanvragen;
    }
}
