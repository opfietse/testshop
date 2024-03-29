package nl.minbzk.rig.demo.testshop.service;

import nl.minbzk.rig.demo.testshop.jpa.model.Customer;
import nl.minbzk.rig.demo.testshop.jpa.model.Order;
import nl.minbzk.rig.demo.testshop.jpa.repositories.CustomerRepository;
import nl.minbzk.rig.demo.testshop.jpa.repositories.OrderRepository;
import nl.minbzk.rig.demo.testshop.jpa.repositories.OrderReviewerRepository;
import nl.minbzk.rig.demo.testshop.mappers.OrderMapper;
import nl.minbzk.rig.demo.testshop.rest.model.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderReviewerRepository orderReviewerRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    VoucherService voucherService;

    @Test
    void payOrder_enoughPaid_orderShiipped() {
        // Given
        Payment payment = new Payment().amount(10.1d);
        Customer dbCustomer = new Customer("Mark", "1", "mark@reuvekamp.nl").id(1L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(dbCustomer));
        when(orderService.findById(1L)).thenReturn(
          Optional.of(
            new Order()
              .id(1L)
              .orderStatus(Order.ORDER_STATUS.APPROVED)
              .customer(dbCustomer)
          )
        );

        // When
        orderService.payOrder(payment, 1L, 1L);

        // Then
        verify(voucherService).issueVoucher(1L, 1L);
    }
}
