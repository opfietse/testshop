package nl.minbzk.rig.demo.testshop.jpa.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    public BaseEntity id(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }
}
