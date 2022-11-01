package guru.springframework.orderservice.repositories;

import guru.springframework.orderservice.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerVersionTest {

    @Autowired
    CustomerRepository customerRepository;

    Customer savedCustomer ;
    @BeforeEach
    void setup(){
        Customer customer = new Customer();
        customer.setCustomerName("Test Customer 1");
        savedCustomer = customerRepository.saveAndFlush(customer);
    }

    @Test
    @Rollback(value=false)
    void updateCustomerName(){
        assertTrue(savedCustomer.getVersion()==0);

        savedCustomer.setCustomerName("Test Customer 2");
        Customer savedCustomer1 = customerRepository.saveAndFlush(savedCustomer);
        Customer savedCustomer2 = customerRepository.saveAndFlush(savedCustomer);
        assertTrue(savedCustomer2.getVersion()==1);

        customerRepository.delete(savedCustomer2);
    }

    @Test
    @Rollback(value=false)
    void noUpdate(){
        assertTrue(savedCustomer.getVersion()==0);
        Customer savedCustomer1 = customerRepository.saveAndFlush(savedCustomer);

        assertTrue(savedCustomer1.getVersion()==0);
         customerRepository.delete(savedCustomer1);
    }

}
