package nl.minbzk.rig.demo.testshop.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "order_reviewers")
public class OrderReviewer extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy =  "orderReviewer")
    private List<Order> orders = new ArrayList<>();

    public OrderReviewer id(Long id) {
        this.id = id;
        return this;
    }

    public OrderReviewer name(String name) {
        this.name = name;
        return this;
    }

    public OrderReviewer order(Order order) {
        this.orders.add(order);
        return this;
    }

    public String getName() {
        return name;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
