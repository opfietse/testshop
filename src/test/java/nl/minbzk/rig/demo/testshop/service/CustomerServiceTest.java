package nl.minbzk.rig.demo.testshop.service;

import nl.minbzk.rig.demo.testshop.jpa.repositories.CustomerRepository;
import nl.minbzk.rig.demo.testshop.rest.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void addCustomer_validEmailAddress_customerAdded() {
        // Given

        // When
        customerService.addCustomer(
          new Customer()
            .emailAddress("ditis54positieslangendatmagbestvolgensdestandaardaaaaa@reuvekamp.nl")
        );

        // Then
        verify(customerRepository).save(any());
    }
}
