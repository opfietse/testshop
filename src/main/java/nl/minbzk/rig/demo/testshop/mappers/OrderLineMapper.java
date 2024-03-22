package nl.minbzk.rig.demo.testshop.mappers;

import nl.minbzk.rig.demo.testshop.jpa.model.Order;
import nl.minbzk.rig.demo.testshop.jpa.repositories.ArticleRepository;
import nl.minbzk.rig.demo.testshop.rest.model.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {
    @Autowired
    private ArticleRepository articleRepository;

    public OrderLine jpaToApi(nl.minbzk.rig.demo.testshop.jpa.model.OrderLine orderLine) {
        return new OrderLine()
          .id(orderLine.getId())
          .orderId(orderLine.getOrder().getId())
          .article(orderLine.getArticle().getName())
          .unitPrice(orderLine.getArticle().getPrice())
          .quantity(orderLine.getQuantity())
          ;
    }

    public nl.minbzk.rig.demo.testshop.jpa.model.OrderLine apiToJpa(OrderLine orderLine, Order customer) {
        return new nl.minbzk.rig.demo.testshop.jpa.model.OrderLine()
          .quantity(orderLine.getQuantity())
          .article(articleRepository.findByName(orderLine.getArticle()).get())
          .assignToOrder(customer);
    }
}
