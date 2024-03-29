package nl.minbzk.rig.demo.testshop.service;

import nl.minbzk.rig.demo.testshop.jpa.model.Customer;
import nl.minbzk.rig.demo.testshop.jpa.repositories.CustomerRepository;
import nl.minbzk.rig.demo.testshop.mappers.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> fetchAllCustomers() {
        return customerRepository.findAll();
    }

    public Long addCustomer(nl.minbzk.rig.demo.testshop.rest.model.Customer customer) {
        checkEmailAddress(customer.getEmailAddress());

        Customer customer1 = CustomerMapper.apiToJpa(customer);
        customerRepository.save(customer1);

        return customer1.getId();
    }

    private void checkEmailAddress(String emailAddress) {
        if (!Pattern.compile("^[a-zA-z0-9.]{1,50}@[a-z]{1,50}\\.[a-z]{2,3}$").matcher(emailAddress).matches()) {
            throw new IllegalArgumentException(emailAddress + " is not a valid email address");
        }
    }
}
