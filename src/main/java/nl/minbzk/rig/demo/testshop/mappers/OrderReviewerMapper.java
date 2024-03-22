package nl.minbzk.rig.demo.testshop.mappers;

import nl.minbzk.rig.demo.testshop.rest.model.OrderReviewer;

public class OrderReviewerMapper {
    public static OrderReviewer jpaToApi(nl.minbzk.rig.demo.testshop.jpa.model.OrderReviewer customer) {
        return new OrderReviewer()
          .id(customer.getId())
          .name(customer.getName())
          ;
    }

    public static nl.minbzk.rig.demo.testshop.jpa.model.OrderReviewer apiToJpa(OrderReviewer customer) {
        return new nl.minbzk.rig.demo.testshop.jpa.model.OrderReviewer()
          .name(customer.getName())
          ;
    }
}
