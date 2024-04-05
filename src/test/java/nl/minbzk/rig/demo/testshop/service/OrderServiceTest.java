package nl.minbzk.rig.demo.testshop.service;

import nl.minbzk.rig.demo.testshop.jpa.model.Article;
import nl.minbzk.rig.demo.testshop.jpa.model.Order;
import nl.minbzk.rig.demo.testshop.jpa.model.OrderLine;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Test
    void calculateOrderPrice_oneOrderLine_priceOfOrderline() {
        Order order = new Order()
          .orderLine(new OrderLine().quantity(2).article(new Article().price(23.50)));

        Assertions.assertThat(orderService.calculateOrderPrice(order)).isEqualTo(47.0d);
    }

    @Test
    void calculateOrderPrice_twoOrderLines_totalPriceOfTheTwoOrderlines() {
        Order order = new Order()
          .orderLine(new OrderLine().quantity(2).article(new Article().price(23.50)))
          .orderLine(new OrderLine().quantity(2).article(new Article().price(23.50)))
          ;

        Assertions.assertThat(orderService.calculateOrderPrice(order)).isEqualTo(94.0d);
    }
}
