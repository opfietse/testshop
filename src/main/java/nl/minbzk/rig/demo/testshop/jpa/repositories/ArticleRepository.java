package nl.minbzk.rig.demo.testshop.jpa.repositories;

import nl.minbzk.rig.demo.testshop.jpa.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByName(String name);
}
