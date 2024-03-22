package nl.minbzk.rig.demo.testshop.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "order_lines")
public class OrderLine extends BaseEntity {
    @Column
    Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    public OrderLine quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderLine article(Article article) {
        this.article = article;
        return this;
    }

    public OrderLine assignToOrder(Order order) {
        this.order = order;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Order getOrder() {
        return order;
    }

    public Article getArticle() {
        return article;
    }
}
