package nl.minbzk.rig.demo.testshop.jpa.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();

    public Order id(Long id) {
        this.id = id;
        return this;
    }

    public Order orderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public Order orderStatusDate(LocalDate orderStatusDate) {
        this.orderStatusDate = orderStatusDate;
        return this;
    }

    public Order orderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public Order orderLine(OrderLine orderLine) {
        orderLines.add(orderLine);
        orderLine.assignToOrder(this);
        return this;
    }

    public Order customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getOrderStatusDate() {
        return orderStatusDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public OrderReviewer getOrderReviewer() {
        return orderReviewer;
    }

    public abstract class ORDER_STATUS {
        public static final String IN_REVIEW = "IN_REVIEW";
        public static final String APPROVED = "APPROVED";
        public static final String REJECTED = "REJECTED";
        public static final String DELIVERD = "DELIVERD";
        public static final String PAID = "PAID";
    }
}
