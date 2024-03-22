package nl.minbzk.rig.demo.testshop.rest.controller;

import nl.minbzk.rig.demo.testshop.mappers.OrderMapper;
import nl.minbzk.rig.demo.testshop.rest.model.Order;
import nl.minbzk.rig.demo.testshop.rest.model.Payment;
import nl.minbzk.rig.demo.testshop.rest.model.ReviewDecision;
import nl.minbzk.rig.demo.testshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers/{customerId}")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Order> fetchAllOrders(@PathVariable Long customerId) {
        return orderService
                .fetchAllOrders()
                .stream()
                .filter(order -> order.getCustomer().getId().equals(customerId))
                .map(orderMapper::jpaToApi)
                .toList()
                ;
    }

    @PostMapping(value = "orders" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addOrder(@PathVariable Long customerId, @RequestBody Order order) {
        return orderService.addOrder(order, customerId).toString();
    }

    @PostMapping(value = "orders/{orderId}/reviewer/{reviewerId}" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void reviewOrder(@PathVariable Long customerId, @PathVariable Long orderId, @PathVariable Long reviewerId, @RequestBody ReviewDecision reviewDecision) {
        orderService.changeOrder(reviewDecision, customerId, orderId, reviewerId);
    }

    @PostMapping(value = "orders/{orderId}/pay" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void payOrder(@PathVariable Long customerId, @PathVariable Long orderId, @RequestBody Payment payment) {
        orderService.payOrder(payment, customerId, orderId);
    }
}
