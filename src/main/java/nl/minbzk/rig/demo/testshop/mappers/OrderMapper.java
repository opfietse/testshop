package nl.minbzk.rig.demo.testshop.mappers;

import nl.minbzk.rig.demo.testshop.jpa.model.Customer;
import nl.minbzk.rig.demo.testshop.rest.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderMapper {
    @Autowired
    private OrderLineMapper orderLineMapper;

    public Order jpaToApi(nl.minbzk.rig.demo.testshop.jpa.model.Order order) {
        return new Order()
                .id(order.getId())
                .customerId(order.getCustomer().getId())
                .orderDate(order.getOrderDate())
            .orderStatus(order.getOrderStatus())
                .orderStatusDate(order.getOrderStatusDate())
                .orderLines(order.getOrderLines().stream().map(o1 -> orderLineMapper.jpaToApi(o1)).toList())
                ;
    }

    public nl.minbzk.rig.demo.testshop.jpa.model.Order apiToJpa(Order order, Customer customer) {
        return new nl.minbzk.rig.demo.testshop.jpa.model.Order()
                .customer(customer)
                .orderDate(order.getOrderDate())
                .orderStatusDate(order.getOrderStatusDate())
                .orderStatus("REVIEW")
                ;
    }
}
