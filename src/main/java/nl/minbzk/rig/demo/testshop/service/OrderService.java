package nl.minbzk.rig.demo.testshop.service;

import nl.minbzk.rig.demo.testshop.jpa.model.Order;
import nl.minbzk.rig.demo.testshop.jpa.repositories.CustomerRepository;
import nl.minbzk.rig.demo.testshop.jpa.repositories.OrderRepository;
import nl.minbzk.rig.demo.testshop.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderMapper orderMapper;

    public List<Order> fetchAllOrders() {
        return orderRepository.findAll();
    }

    public void addOrder(nl.minbzk.rig.demo.testshop.rest.model.Order order, Long customerId) {
        customerRepository.findById(customerId).ifPresentOrElse(customer -> {
            Order order1 = orderMapper.apiToJpa(order, customer);
            orderRepository.save(order1);
        },() -> {});
    }

//    public void addOrder(nl.minbzk.rig.demo.testshop.rest.model.Order order, Long customerId) {
//        customerRepository.findById(customerId).ifPresentOrElse(customer -> {
//            Order order1 = OrderMapper.apiToJpa(order, customer);
//            orderRepository.save(order1);
//        });
//    }
}
