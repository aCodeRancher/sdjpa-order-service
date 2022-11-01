package guru.springframework.orderservice.bootstrap;

import guru.springframework.orderservice.domain.*;
import guru.springframework.orderservice.repositories.CustomerRepository;
import guru.springframework.orderservice.repositories.OrderHeaderRepository;
import guru.springframework.orderservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 6/8/22.
 */
@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    BootstrapOrderService bootstrapOrderService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

//    @Transactional
//    public void readOrderData(){
//        OrderHeader orderHeader = orderHeaderRepository.findById(1L).get();
//
//        orderHeader.getOrderLines().forEach(ol -> {
//            System.out.println(ol.getProduct().getDescription());
//
//            ol.getProduct().getCategories().forEach(cat -> {
//                System.out.println(cat.getDescription());
//            });
//        });
//    }

    @Override
   public void run(String... args) throws Exception {
         bootstrapOrderService.readOrderData();

    Customer customer = new Customer();
      //  customer.setCustomerName("Testing Version");
        Customer savedCustomer = customerRepository.save(customer);
        System.out.println("Version is: " + savedCustomer.getVersion());

       // savedCustomer.setCustomerName("Testing Version 2");
        Customer savedCustomer2 = customerRepository.save(savedCustomer);
        System.out.println("Version is: " + savedCustomer2.getVersion());

       // savedCustomer2.setCustomerName("Testing Version 3");
        Customer savedCustomer3 = customerRepository.save(savedCustomer2);
        System.out.println("Version is: " + savedCustomer3.getVersion());

        customerRepository.delete(savedCustomer3);

      /* OrderHeader orderHeader = new OrderHeader();
        OrderLine orderLine = new OrderLine();
        Set<OrderLine> orderLineSet = new HashSet();

        Product product = productRepository.findById(1l).get();
        orderLine.setProduct(product);
        orderLine.setQuantityOrdered(1);

        orderLineSet.add(orderLine);
        orderHeader.setOrderLines(orderLineSet);
        orderLineSet.iterator().next().setOrderHeader(orderHeader);

        OrderHeader savedOrderHeader = orderHeaderRepository.saveAndFlush(orderHeader);

        System.out.println("Version of Order Header: " + savedOrderHeader.getVersion());
        System.out.println("Version of Order line: " + savedOrderHeader.getOrderLines().iterator().next().getVersion());
        System.out.println("Order Header id : " + savedOrderHeader.getId());

     //   OrderHeader attachedOrderHeader =   orderHeaderRepository.findById(savedOrderHeader.getId()).get();

      //  OrderLine orderline1 = attachedOrderHeader.getOrderLines().iterator().next();
      //  orderline1.setQuantityOrdered(2);

        OrderHeader savedOrderHeader1 = orderHeaderRepository.saveAndFlush(savedOrderHeader);
         System.out.println("Version of Order Header: " +
                 orderHeaderRepository.findById(savedOrderHeader1.getId()).get().getVersion());
         System.out.println("Version of Order line: " + savedOrderHeader1.getOrderLines().iterator().next().getVersion());

        OrderHeader savedOrderHeader2 = orderHeaderRepository.saveAndFlush(savedOrderHeader1);
        System.out.println("Version of Order Header: " +
                orderHeaderRepository.findById(savedOrderHeader2.getId()).get().getVersion());

        orderHeaderRepository.deleteById(savedOrderHeader2.getId());
         */
    }
}






