package nl.minbzk.rig.demo.testshop.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "subcategories")
public class Subcategory extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private Category category;

    public Subcategory id(Long id) {
        this.id = id;
        return this;
    }

    public Subcategory name(String name) {
        this.name = name;
        return this;
    }

    public Subcategory categorie(Category category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }
}
