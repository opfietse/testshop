package nl.minbzk.rig.demo.testshop.rest.model;

public class ReviewDecision {
    private String decision;

    public ReviewDecision decision(String decision) {
        this.decision = decision;
        return this;
    }

    public String getDecision() {
        return decision;
    }
}
