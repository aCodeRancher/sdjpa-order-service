package guru.springframework.orderservice.repositories;

import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.domain.OrderLine;
import guru.springframework.orderservice.domain.OrderStatus;
import guru.springframework.orderservice.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderHeaderVersionTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    ProductRepository productRepository;

    private OrderHeader savedOrderHeader;

    @BeforeEach
    void setup(){
        OrderHeader orderHeader = new OrderHeader();
        OrderLine orderLine = new OrderLine();
        Set<OrderLine> orderLineSet = new HashSet();

        Product product = productRepository.findById(1l).get();
        orderLine.setProduct(product);
        orderLine.setQuantityOrdered(1);

        orderLineSet.add(orderLine);
        orderHeader.setOrderLines(orderLineSet);
        orderLineSet.iterator().next().setOrderHeader(orderHeader);
        orderHeader.setOrderStatus(OrderStatus.NEW);
        savedOrderHeader = orderHeaderRepository.saveAndFlush(orderHeader);
    }

    @Test
    @Rollback(value=false)
    void updateOrderStatus(){
        assertTrue(savedOrderHeader.getVersion()==0);
        savedOrderHeader.setOrderStatus(OrderStatus.IN_PROCESS);
        OrderHeader savedOrderHeader1 = orderHeaderRepository.saveAndFlush(savedOrderHeader);

        assertTrue(savedOrderHeader1.getVersion()==1);
        orderHeaderRepository.deleteById(savedOrderHeader1.getId());
    }

    @Test
    @Rollback(value=false)
    void updateOrderLineQuantity(){
        assertTrue(savedOrderHeader.getVersion()==0);
        assertTrue(savedOrderHeader.getOrderLines().iterator().next().getVersion()==0);

        OrderLine orderline1 = savedOrderHeader.getOrderLines().iterator().next();
        orderline1.setQuantityOrdered(2);

        OrderHeader savedOrderHeader1 = orderHeaderRepository.saveAndFlush(savedOrderHeader);

        assertTrue(savedOrderHeader1.getVersion()==0);
        assertTrue(savedOrderHeader1.getOrderLines().iterator().next().getVersion()==1);

        orderHeaderRepository.deleteById(savedOrderHeader1.getId());
    }
}
