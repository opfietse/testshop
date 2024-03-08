package nl.minbzk.rig.demo.testshop.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity(name = "customers")
public class Customer extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String customerNumber;
    @Column(nullable = false)
    private String emailAddress;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer() {
    }

    public Customer(String name, String customerNumber, String emailAddress) {
        this.name = name;
        this.customerNumber = customerNumber;
        this.emailAddress = emailAddress;
    }

    public Customer id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer name(String name) {
        this.name = name;
        return this;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public Customer customerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Customer emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }
}
