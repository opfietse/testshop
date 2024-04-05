package nl.minbzk.rig.demo.testshop.service;

import nl.minbzk.rig.demo.testshop.jpa.model.Order;
import nl.minbzk.rig.demo.testshop.jpa.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Test
    void deliverOrder_deliverableOrdersPresent_ordersDelivered() {
        // Given
        Order deliverableOrder1 = new Order().id(1L).orderStatus(Order.ORDER_STATUS.WAREHOUSE);
        Order deliverableOrder2 = new Order().id(2L).orderStatus(Order.ORDER_STATUS.WAREHOUSE);
        Order nonDeliverableOrder1 = new Order().id(3L).orderStatus(Order.ORDER_STATUS.IN_REVIEW);

        when(orderRepository.findAll()).thenReturn(List.of(deliverableOrder1, nonDeliverableOrder1, deliverableOrder2));

        // When
        orderService.deliverOrders();

        // Then
        verify(orderRepository).findAll();
        verify(orderRepository).save(new Order().id(1L).orderStatus(Order.ORDER_STATUS.DELIVERED));
        verify(orderRepository).save(new Order().id(2L).orderStatus(Order.ORDER_STATUS.DELIVERED));
    }
}
