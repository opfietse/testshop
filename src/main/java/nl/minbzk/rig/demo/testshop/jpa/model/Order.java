package nl.minbzk.rig.demo.testshop.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity(name = "orders")
public class Order extends BaseEntity {
    private LocalDate orderDate;
    private LocalDate orderStatusDate;
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="order_reviewer_id")
    private OrderReviewer orderReviewer;

    @ManyToOne
    @JoinColumn(name="subcategory_id", nullable = false)
    private Subcategory subcategory;

    public Order id(Long id) {
        this.id = id;
        return this;
    }

    public Order aanvraagdatum(LocalDate aanvraagdatum) {
        this.orderDate = aanvraagdatum;
        return this;
    }

    public Order beslissingsdatum(LocalDate beslissingsdatum) {
        this.orderStatusDate = beslissingsdatum;
        return this;
    }

    public Order beslissing(String beslissing) {
        this.orderStatus = beslissing;
        return this;
    }

    public Order subcategorie(Subcategory subcategory) {
        this.subcategory = subcategory;
        return this;
    }

    public Order aanvrager(Customer aanvrager) {
        this.customer = aanvrager;
        return this;
    }

    public Order behandelaar(OrderReviewer orderReviewer) {
        this.orderReviewer = orderReviewer;
        return this;
    }
}
