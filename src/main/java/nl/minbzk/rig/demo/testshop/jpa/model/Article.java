package nl.minbzk.rig.demo.testshop.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "articles")
public class Article extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "subcategorie_id", nullable = false)
    private Subcategory subcategory;

    public Article id(Long id) {
        this.id = id;
        return this;
    }

    public Article name(String name) {
        this.name = name;
        return this;
    }

    public Article subcategorie(Subcategory subcategory) {
        this.subcategory = subcategory;
        return this;
    }

    public String getName() {
        return name;
    }
}
