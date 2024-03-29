package nl.minbzk.rig.demo.testshop.service;

import nl.minbzk.rig.demo.testshop.jpa.model.Customer;
import nl.minbzk.rig.demo.testshop.jpa.model.Order;
import nl.minbzk.rig.demo.testshop.jpa.model.OrderReviewer;
import nl.minbzk.rig.demo.testshop.jpa.repositories.CustomerRepository;
import nl.minbzk.rig.demo.testshop.jpa.repositories.OrderRepository;
import nl.minbzk.rig.demo.testshop.jpa.repositories.OrderReviewerRepository;
import nl.minbzk.rig.demo.testshop.rest.model.ReviewDecision;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderReviewerRepository orderReviewerRepository;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void changeOrder_newStatusApproved_orderApproved() {
        // Given
        when(orderReviewerRepository.findById(3L)).thenReturn(Optional.of(new OrderReviewer().id(3L)));
        when(orderRepository.findById(2L)).thenReturn(Optional.of(new Order().id(2L).orderStatus(Order.ORDER_STATUS.IN_REVIEW)));
        when(customerRepository.findById(1L)).thenReturn(Optional.of(new Customer().id(1L)));

        // When
        orderService.changeOrder(
          new ReviewDecision().decision(Order.ORDER_STATUS.APPROVED.toString()),
          1L,
          2L,
          3L
        );

        // Then
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.captor();
        verify(orderRepository).save(orderCaptor.capture());
        assertThat(orderCaptor.getValue().getOrderStatus()).isEqualTo(Order.ORDER_STATUS.APPROVED.toString());
    }
}
