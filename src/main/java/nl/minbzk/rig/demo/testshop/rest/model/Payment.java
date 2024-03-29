package nl.minbzk.rig.demo.testshop.rest.model;

public class Payment {
    private Double amount;

    public Payment amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Double getAmount() {
        return amount;
    }
}
