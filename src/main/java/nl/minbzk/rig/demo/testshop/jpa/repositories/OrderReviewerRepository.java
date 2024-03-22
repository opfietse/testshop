package nl.minbzk.rig.demo.testshop.jpa.repositories;

import nl.minbzk.rig.demo.testshop.jpa.model.OrderReviewer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderReviewerRepository extends JpaRepository<OrderReviewer, Long> {
    Optional<OrderReviewer> findByName(String name);
}
