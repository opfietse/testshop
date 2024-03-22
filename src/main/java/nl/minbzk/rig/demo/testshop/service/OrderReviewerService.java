package nl.minbzk.rig.demo.testshop.service;

import nl.minbzk.rig.demo.testshop.jpa.model.OrderReviewer;
import nl.minbzk.rig.demo.testshop.jpa.repositories.OrderReviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderReviewerService {
    @Autowired
    private OrderReviewerRepository orderReviewerRepository;

    public List<OrderReviewer> fetchAllOrderReviewers() {
        return orderReviewerRepository.findAll();
    }
}
