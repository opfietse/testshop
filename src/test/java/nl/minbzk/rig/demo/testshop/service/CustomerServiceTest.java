package nl.minbzk.rig.demo.testshop.service;

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
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
//        Fairy fairy = Fairy.create();
//        Person person = fairy.person();
//        System.out.println(person.getEmail());

        // When
        customerService.addCustomer(
          new Customer()
            .emailAddress("ditis54positieslangendatmagbestvolgensdestandaardaaaaa@reuvekamp.nl")
//            .emailAddress(person.getEmail())
        );

        // Then
        verify(customerRepository).save(any());
    }
}
