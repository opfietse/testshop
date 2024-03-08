package nl.minbzk.rig.demo.testshop.service;

import nl.minbzk.rig.demo.testshop.CustomerMapper;
import nl.minbzk.rig.demo.testshop.jpa.model.Customer;
import nl.minbzk.rig.demo.testshop.jpa.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> fetchAllCustomers() {
        return customerRepository.findAll();
    }

    public void addCustomer(nl.minbzk.rig.demo.testshop.rest.model.Customer customer) {
        Customer customer1 = CustomerMapper.apiToJpa(customer);
        customerRepository.save(customer1);
    }
}
