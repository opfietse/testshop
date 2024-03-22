package nl.minbzk.rig.demo.testshop.rest.controller;

import nl.minbzk.rig.demo.testshop.mappers.OrderReviewerMapper;
import nl.minbzk.rig.demo.testshop.rest.model.OrderReviewer;
import nl.minbzk.rig.demo.testshop.service.OrderReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderReviewerController {
    @Autowired
    private OrderReviewerService orderService;

    @GetMapping(value = "/reviewers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<OrderReviewer> fetchAllOrderReviewers() {
        return orderService
                .fetchAllOrderReviewers()
                .stream()
                .map(OrderReviewerMapper::jpaToApi)
                .toList()
                ;
    }
}
