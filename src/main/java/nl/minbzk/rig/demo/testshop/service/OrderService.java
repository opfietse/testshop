package nl.minbzk.rig.demo.testshop.service;

import nl.minbzk.rig.demo.testshop.TestshopException;
import nl.minbzk.rig.demo.testshop.jpa.model.Order;
import nl.minbzk.rig.demo.testshop.jpa.repositories.CustomerRepository;
import nl.minbzk.rig.demo.testshop.jpa.repositories.OrderRepository;
import nl.minbzk.rig.demo.testshop.jpa.repositories.OrderReviewerRepository;
import nl.minbzk.rig.demo.testshop.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderReviewerRepository orderReviewerRepository;
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
            order1
              .orderStatus(Order.ORDER_STATUS.IN_REVIEW)
              .orderDate(LocalDate.now())
            ;
            orderRepository.save(order1);
        }, () -> {
            throw new TestshopException("Customer not found");
        });
    }

    public void changeOrder(nl.minbzk.rig.demo.testshop.rest.model.Order order, Long customerId, Long orderId, Long reviewerId) {
        customerRepository.findById(customerId).ifPresentOrElse(
          customer -> orderReviewerRepository.findById(reviewerId).ifPresentOrElse(
            reviewer -> orderRepository.findById(orderId).ifPresentOrElse(
              dbOrder -> {
                  dbOrder
                    .orderReviewer(reviewer)
                    .orderStatus(order.getOrderStatus())
                    .orderStatusDate(LocalDate.now());
                  orderRepository.save(dbOrder);
              }, () -> {
                  throw new TestshopException("Order not found");
              }), () -> {
                throw new TestshopException("Reviewer not found");
            }), () -> {
              throw new TestshopException("Customer not found");
          });
    }
}
