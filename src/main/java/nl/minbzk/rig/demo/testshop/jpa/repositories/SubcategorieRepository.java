package nl.minbzk.rig.demo.testshop.jpa.repositories;

import nl.minbzk.rig.demo.testshop.jpa.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategorieRepository extends JpaRepository<Subcategory, Long> {
}
