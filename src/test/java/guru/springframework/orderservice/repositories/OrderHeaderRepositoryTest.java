package guru.springframework.orderservice.repositories;

import guru.springframework.orderservice.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    Product product;

    Customer customer;

    @BeforeEach
    void setUp() {
        Product newProduct = new Product();
        newProduct.setProductStatus(ProductStatus.NEW);
        newProduct.setDescription("test product");
        product = productRepository.saveAndFlush(newProduct);

        customer = new Customer();
        customer.setName("John Tom");
        customer.setEmail("jt@gmail.com");
        customer.setPhone("5712401234");
        Address address = new Address();
        address.setAddress("1000, Taylor Street");
        address.setCity("Arlington");
        address.setState("VA");
        address.setZipCode("22201");
        customer.setAddress(address);
    }

    @Test
    void testSaveOrderWithLine() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);
        orderLine.setProduct(product);

        orderHeader.addOrderLine(orderLine);
        Set<OrderHeader> orderHeaderSet = new HashSet<>();
        orderHeaderSet.add(orderHeader);
        customer.setOrderHeaderSet(orderHeaderSet);

        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);
        Customer savedCustomer = customerRepository.save(customer);
        orderHeaderRepository.flush();
        OrderHeader fetchedOrder = orderHeaderRepository.findById(savedOrder.getId()).get();
        assertTrue(fetchedOrder.getCustomer().getPhone().equals("5712401234"));
        assertNotNull(fetchedOrder);
        assertEquals(fetchedOrder.getOrderLines().size(), 1);

        Customer fetchedCustomer = customerRepository.findById(savedCustomer.getId()).get();
        Iterator<OrderHeader> fetchedOrderItr = fetchedCustomer.getOrderHeaderSet().iterator();
        OrderHeader oh = fetchedOrderItr.next();
        assertTrue(oh.getId()!=null);

        assertTrue(fetchedCustomer.getEmail().equals("jt@gmail.com"));
        assertTrue(fetchedCustomer.getName().equals("John Tom"));
        assertTrue(fetchedCustomer.getAddress().getZipCode().equals("22201"));
     }

    @Test
    void testSaveOrder() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);

        Set<OrderHeader> orderHeaderSet = new HashSet<>();
        orderHeaderSet.add(orderHeader);
        customer.setOrderHeaderSet(orderHeaderSet);
        customerRepository.save(customer);

       Customer foundCustomer = orderHeaderRepository.findCustomerByName("John Tom");
       assertEquals(foundCustomer.getOrderHeaderSet().size(),1);

    }
}