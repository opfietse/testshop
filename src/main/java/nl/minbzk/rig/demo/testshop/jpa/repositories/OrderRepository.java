package nl.minbzk.rig.demo.testshop.jpa.repositories;

import nl.minbzk.rig.demo.testshop.jpa.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
