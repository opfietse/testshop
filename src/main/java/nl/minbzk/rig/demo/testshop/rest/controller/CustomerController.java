package nl.minbzk.rig.demo.testshop.rest.controller;

import nl.minbzk.rig.demo.testshop.mappers.CustomerMapper;
import nl.minbzk.rig.demo.testshop.rest.model.Customer;
import nl.minbzk.rig.demo.testshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public String addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer).toString();
    }
}
