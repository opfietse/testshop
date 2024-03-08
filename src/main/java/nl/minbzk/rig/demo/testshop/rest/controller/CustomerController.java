package nl.minbzk.rig.demo.testshop.rest.controller;

import nl.minbzk.rig.demo.testshop.CustomerMapper;
import nl.minbzk.rig.demo.testshop.rest.model.Customer;
import nl.minbzk.rig.demo.testshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Customer> fetchAllCustomers() {
        return customerService
          .fetchAllCustomers()
          .stream()
          .map(CustomerMapper::jpaToApi)
          .toList()
          ;
    }

    @PostMapping(value = "customers" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }
}
