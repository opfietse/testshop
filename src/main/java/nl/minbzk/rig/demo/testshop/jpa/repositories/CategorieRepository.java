package nl.minbzk.rig.demo.testshop.jpa.repositories;

import nl.minbzk.rig.demo.testshop.jpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Category, Long> {
}
