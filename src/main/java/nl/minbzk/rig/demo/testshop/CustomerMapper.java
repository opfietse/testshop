package nl.minbzk.rig.demo.testshop;

import nl.minbzk.rig.demo.testshop.rest.model.Customer;

public class CustomerMapper {
    public static Customer jpaToApi(nl.minbzk.rig.demo.testshop.jpa.model.Customer customer) {
        return new Customer()
          .id(customer.getId())
          .name(customer.getName())
          .customerNumber(customer.getCustomerNumber())
          .emailAddress(customer.getEmailAddress())
          ;
    }

    public static nl.minbzk.rig.demo.testshop.jpa.model.Customer apiToJpa(Customer customer) {
        return new nl.minbzk.rig.demo.testshop.jpa.model.Customer()
          .name(customer.getName())
          .customerNumber(customer.getCustomerNumber())
          .emailAddress(customer.getEmailAddress())
          ;
    }
}
