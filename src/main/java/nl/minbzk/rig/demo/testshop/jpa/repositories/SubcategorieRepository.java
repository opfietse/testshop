package nl.minbzk.rig.demo.testshop.jpa.repositories;

import nl.minbzk.rig.demo.testshop.jpa.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubcategorieRepository extends JpaRepository<Subcategory, Long> {
    Optional<Subcategory> findByName(String name);
}
