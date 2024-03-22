package nl.minbzk.rig.demo.testshop.service;

import nl.minbzk.rig.demo.testshop.TestshopException;
import nl.minbzk.rig.demo.testshop.jpa.model.Order;
import nl.minbzk.rig.demo.testshop.jpa.repositories.CustomerRepository;
import nl.minbzk.rig.demo.testshop.jpa.repositories.OrderRepository;
import nl.minbzk.rig.demo.testshop.jpa.repositories.OrderReviewerRepository;
import nl.minbzk.rig.demo.testshop.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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

    public Long addOrder(nl.minbzk.rig.demo.testshop.rest.model.Order order, Long customerId) {
        AtomicLong orderId = new AtomicLong();

        customerRepository.findById(customerId).ifPresentOrElse(customer -> {
            Order order1 = orderMapper.apiToJpa(order, customer);
            order1
              .orderStatus(Order.ORDER_STATUS.IN_REVIEW)
              .orderDate(LocalDate.now())
            ;
            orderId.set(orderRepository.save(order1).getId());
        }, () -> {
            throw new TestshopException("Customer not found");
        });

        return orderId.get();
    }

    public void changeOrder(nl.minbzk.rig.demo.testshop.rest.model.ReviewDecision reviewDecision, Long customerId, Long orderId, Long reviewerId) {
        customerRepository.findById(customerId).ifPresentOrElse(
          customer -> orderReviewerRepository.findById(reviewerId).ifPresentOrElse(
            reviewer -> orderRepository.findById(orderId).ifPresentOrElse(
              dbOrder -> {
                  if (reviewDecision.getDecision().equals(Order.ORDER_STATUS.APPROVED) || reviewDecision.getDecision().equals(Order.ORDER_STATUS.REJECTED)) {
                      if (dbOrder.getOrderStatus().equals(Order.ORDER_STATUS.IN_REVIEW)) {
                          dbOrder
                            .orderReviewer(reviewer)
                            .orderStatus(reviewDecision.getDecision())
                            .orderStatusDate(LocalDate.now());
                      } else throw new IllegalStateException("Order not in review");
                  } else throw new IllegalArgumentException("No review status given");
                  orderRepository.save(dbOrder);
              }, () -> {
                  throw new TestshopException("Order not found");
              }), () -> {
                throw new TestshopException("Reviewer not found");
            }), () -> {
              throw new TestshopException("Customer not found");
          });
    }

    public void payOrder(nl.minbzk.rig.demo.testshop.rest.model.Payment payment, Long customerId, Long orderId) {
        customerRepository.findById(customerId).ifPresentOrElse(
            customer -> orderRepository.findById(orderId).ifPresentOrElse(
              dbOrder -> {
                    if (dbOrder.getOrderStatus().equals(Order.ORDER_STATUS.APPROVED)) {
                        if (payment.getAmount() >= calculateOrderPrice(dbOrder)) {
                            dbOrder
                              .orderStatus(Order.ORDER_STATUS.PAID)
                              .orderStatusDate(LocalDate.now());
                        } else throw new IllegalArgumentException("Given amount is not sufficient to pay the whole order (" + calculateOrderPrice(dbOrder) + ")");
                    } else throw new IllegalStateException("Order not approved");

                  orderRepository.save(dbOrder);
              }, () -> {
                  throw new TestshopException("Order not found");
            }), () -> {
              throw new TestshopException("Customer not found");
          });
    }

    @Scheduled(fixedRateString = "10000")
    public void shipOrders() {
        System.out.println("shipping paid orders ...");

        fetchAllOrders()
          .stream()
          .filter(order -> order.getOrderStatus().equals(Order.ORDER_STATUS.PAID))
          .forEach(order -> {
              System.out.println("  ship order #" + order.getId());
              order.orderStatus(Order.ORDER_STATUS.WAREHOUSE);
              orderRepository.save(order);
          });
    }

    @Scheduled(initialDelayString = "4000", fixedRateString = "10000")
    public void deliverOrders() {
        System.out.println("delivering orders ...");

        fetchAllOrders()
          .stream()
          .filter(order -> order.getOrderStatus().equals(Order.ORDER_STATUS.WAREHOUSE))
          .forEach(order -> {
              System.out.println("  deliver order #" + order.getId());
              order.orderStatus(Order.ORDER_STATUS.DELIVERD);
              orderRepository.save(order);
          });
    }

    private Double calculateOrderPrice(Order order) {
        return order
          .getOrderLines()
          .stream()
          .mapToDouble(orderline -> orderline.getQuantity() * orderline.getArticle()    .getPrice())
          .sum();
    }
}
