package nl.minbzk.rig.demo.testshop.jpa.repositories;

import nl.minbzk.rig.demo.testshop.jpa.model.OrderReviewer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderReviewerRepository extends JpaRepository<OrderReviewer, Long> {
}
