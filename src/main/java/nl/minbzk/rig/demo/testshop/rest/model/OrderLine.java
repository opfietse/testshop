package nl.minbzk.rig.demo.testshop.rest.model;

public class OrderLine {
    private Long id;
    private Long orderId;
    private String article;
    private Double unitPrice;

    private Integer quantity;

    public OrderLine id(Long id) {
        this.id = id;
        return this;
    }

    public OrderLine orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderLine article(String article) {
        this.article = article;
        return this;
    }

    public OrderLine unitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public OrderLine quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getArticle() {
        return article;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }
}
