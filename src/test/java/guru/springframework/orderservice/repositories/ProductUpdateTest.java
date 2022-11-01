package guru.springframework.orderservice.repositories;

import guru.springframework.orderservice.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductUpdateTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void updateProductQuantity(){
       Product product1 =  productRepository.findByDescription("PRODUCT1").get();
       assertTrue(product1.getQuantityOnHand()==10);
       assertTrue(product1.getVersion()==0);
       product1.setQuantityOnHand(20);
       Product updatedProduct = productRepository.saveAndFlush(product1);
       Product found = productRepository.findById(updatedProduct.getId()).get();
       assertTrue(found.getQuantityOnHand()==20);
       assertTrue(found.getVersion()==1);
    }
}
