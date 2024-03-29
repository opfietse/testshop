package nl.minbzk.rig.demo.testshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoucherService {
    @Autowired
    OrderService orderService;

    public void issueVoucher(Long customerId, Long orderId) {
        System.out.println("Issuing a voucher for customer " + customerId + " ...");

        orderService.findById(orderId).ifPresentOrElse(
          order ->
            System.out.println(orderService.calculateOrderPrice(order) / 10),
          () -> System.out.println("Order with id " + orderId + " not found!")
        );
    }
}
