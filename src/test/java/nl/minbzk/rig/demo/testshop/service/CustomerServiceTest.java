package nl.minbzk.rig.demo.testshop.service;

import net.datafaker.Faker;
import nl.minbzk.rig.demo.testshop.jpa.model.Customer;
import nl.minbzk.rig.demo.testshop.jpa.repositories.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    private Faker faker = new Faker();

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void fetchAllCustomers_all_repositoryCalled() {
        // Given
        List<Customer> customersInDatabase = List.of(
            new Customer().id(1L).customerNumber("1").name("C1").emailAddress("c1@orders.nl"),
            new Customer().id(2L).customerNumber("2").name("C2").emailAddress("c2@orders.nl")
        );
        when(customerRepository.findAll()).thenReturn(customersInDatabase);

        // When
        List<Customer> customers = customerService.fetchAllCustomers();

        // Than
        assertThat(customers).hasSize(2);
        assertThat(customers.get(0).getName()).isEqualTo("C1");
        assertThat(customers.get(1).getName()).isEqualTo("C2");

        verify(customerRepository).findAll();
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void a() {
        // Given
        nl.minbzk.rig.demo.testshop.rest.model.Customer customer = new nl.minbzk.rig.demo.testshop.rest.model.Customer()
          .name(faker.name().fullName())
          .emailAddress(faker.internet().emailAddress())
          ;

        System.out.println(customer.getEmailAddress());

        // When / Than
        assertThatNoException().isThrownBy(() -> customerService.addCustomer(customer));
    }
}
