package nl.minbzk.rig.demo.testshop.jpa.setup;

import jakarta.annotation.PostConstruct;
import nl.minbzk.rig.demo.testshop.jpa.model.Order;
import nl.minbzk.rig.demo.testshop.jpa.model.OrderReviewer;
import nl.minbzk.rig.demo.testshop.jpa.model.Customer;
import nl.minbzk.rig.demo.testshop.jpa.model.Category;
import nl.minbzk.rig.demo.testshop.jpa.model.Subcategory;
import nl.minbzk.rig.demo.testshop.jpa.repositories.OrderRepository;
import nl.minbzk.rig.demo.testshop.jpa.repositories.OrderReviewerRepository;
import nl.minbzk.rig.demo.testshop.jpa.repositories.CustomerRepository;
import nl.minbzk.rig.demo.testshop.jpa.repositories.CategorieRepository;
import nl.minbzk.rig.demo.testshop.jpa.repositories.SubcategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"dev"})
public class InsertSomeData {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderReviewerRepository orderReviewerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private SubcategorieRepository subcategorieRepository;

    @PostConstruct
    public void insertSomeData() {
        System.out.println("Inserting some data .....");

        Category category = categorieRepository.save(new Category().name("Verduurzamen woning"));

        Subcategory zonnepanelen = subcategorieRepository.save(new Subcategory().name("Zonnepanelen").categorie(category));
        subcategorieRepository.save(new Subcategory().name("Spouwmuurisolatie").categorie(category));

        OrderReviewer orderReviewer = orderReviewerRepository.save(new OrderReviewer().name("Mark Rutte"));

        Customer customer = customerRepository.save(
          new Customer("Mark Reuvekamp", "987654321", "mark@reuvekamp.nl")
        );

        orderRepository.save(new Order().aanvrager(customer).behandelaar(orderReviewer).subcategorie(zonnepanelen));
    }
}
