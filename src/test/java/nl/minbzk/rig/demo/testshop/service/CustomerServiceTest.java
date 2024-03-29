package nl.minbzk.rig.demo.testshop.service;

import nl.minbzk.rig.demo.testshop.jpa.model.Customer;
import nl.minbzk.rig.demo.testshop.jpa.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void fetchAllCustomers_success_repositoryCalled() {
        // Given
        List<Customer> customersInDatabase = List.of(
            new Customer().id(1L).customerNumber("1").name("C1").emailAddress("c1@orders.nl"),
            new Customer().id(2L).customerNumber("2").name("C2").emailAddress("c2@orders.nl")
        );
        when(customerRepository.findAll()).thenReturn(customersInDatabase);

        // When
        List<Customer> customers = customerService.fetchAllCustomers();

        // Then
        assertThat(customers).hasSize(2);
        assertThat(customers.get(0).getName()).isEqualTo("C1");
        assertThat(customers.get(1).getName()).isEqualTo("C2");

        verify(customerRepository).findAll();
        verifyNoMoreInteractions(customerRepository);
    }
}
