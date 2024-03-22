package nl.minbzk.rig.demo.testshop.rest.model;

public class OrderReviewer {
    private Long id;
    private String name;

    public OrderReviewer id(Long id) {
        this.id = id;
        return this;
    }

    public OrderReviewer name(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
