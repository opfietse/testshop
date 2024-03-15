package nl.minbzk.rig.demo.testshop.jpa.setup;

import jakarta.annotation.PostConstruct;
import nl.minbzk.rig.demo.testshop.jpa.model.*;
import nl.minbzk.rig.demo.testshop.jpa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
    @Autowired
    private ArticleRepository articleRepository;

    @PostConstruct
    public void insertSomeData() {
        System.out.println("Inserting some data .....");

        if (categorieRepository.findByName("Computers").isPresent()) {
            System.out.println("Data already present, skipping.");
            return;
        }

        Category category = categorieRepository.save(new Category().name("Computers"));

        Subcategory sbc = subcategorieRepository.save(new Subcategory().name("SBC").categorie(category));
        subcategorieRepository.save(new Subcategory().name("Monitor").categorie(category));

        Article raspberryPi = articleRepository.save(new Article().name("Raspberry Pi 5 4GB").subcategorie(sbc));

        OrderReviewer orderReviewer = orderReviewerRepository.save(new OrderReviewer().name("Mark Rutte"));

        Customer customer = customerRepository.save(
                new Customer("Mark Reuvekamp", "987654321", "mark@reuvekamp.nl")
        );

        orderRepository.save(
                new Order()
                        .customer(customer)
                        .orderDate(LocalDate.now())
                        .orderLine(
                                new OrderLine()
                                        .article(raspberryPi)
                                        .quantity(2)
                        )
        );
    }
}
