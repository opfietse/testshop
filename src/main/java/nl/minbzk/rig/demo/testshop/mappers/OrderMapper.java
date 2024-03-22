package nl.minbzk.rig.demo.testshop.mappers;

import nl.minbzk.rig.demo.testshop.jpa.model.Customer;
import nl.minbzk.rig.demo.testshop.jpa.model.OrderLine;
import nl.minbzk.rig.demo.testshop.jpa.repositories.ArticleRepository;
import nl.minbzk.rig.demo.testshop.rest.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    @Autowired
    private OrderLineMapper orderLineMapper;

    @Autowired
    private ArticleRepository articleRepository;

    public Order jpaToApi(nl.minbzk.rig.demo.testshop.jpa.model.Order order) {
        return new Order()
          .id(order.getId())
          .customerId(order.getCustomer().getId())
          .orderDate(order.getOrderDate())
          .orderStatus(order.getOrderStatus())
          .orderStatusDate(order.getOrderStatusDate())
          .reviewer(order.getOrderReviewer().getName())
          .orderLines(order.getOrderLines().stream().map(o1 -> orderLineMapper.jpaToApi(o1)).toList())
          ;
    }

    public nl.minbzk.rig.demo.testshop.jpa.model.Order apiToJpa(Order order, Customer customer) {
        nl.minbzk.rig.demo.testshop.jpa.model.Order order1 = new nl.minbzk.rig.demo.testshop.jpa.model.Order()
          .customer(customer)
          .orderDate(order.getOrderDate())
          .orderStatusDate(order.getOrderStatusDate())
          .orderStatus("REVIEW");

        order
          .getOrderLines()
          .forEach(orderline -> order1.orderLine(
            new OrderLine()
              .article(articleRepository.findByName(orderline.getArticle()).get())
              .quantity(orderline.getQuantity())));

        return order1;
    }
}
