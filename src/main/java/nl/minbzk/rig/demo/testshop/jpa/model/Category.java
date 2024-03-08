package nl.minbzk.rig.demo.testshop.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "categories")
public class Category extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Subcategory> subcategories = new ArrayList<>();

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public Category id(Long id) {
        this.id = id;
        return this;
    }

    public Category subcategory(Subcategory subcategory) {
        this.subcategories.add(subcategory);
        return this;
    }
}
