package nl.minbzk.rig.demo.testshop.rest.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Long id;
    private Long customerId;
    private LocalDate orderDate;
    private String orderStatus;
    private LocalDate orderStatusDate;

    private List<OrderLine> orderLines;

    public Order id(Long id) {
        this.id = id;
        return this;
    }

    public Order orderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
        return this;
    }

    public Order customerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public Order orderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public Order orderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public Order orderStatusDate(LocalDate orderStatusDate) {
        this.orderStatusDate = orderStatusDate;
        return this;
    }

    public Long getId() {
        return id;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public LocalDate getOrderStatusDate() {
        return orderStatusDate;
    }
}
