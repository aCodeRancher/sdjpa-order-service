package guru.springframework.orderservice.repositories;

import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 * Created by jt on 12/5/21.
 */
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

    @Query(value= " from Customer  as c inner join c.orderHeaderSet as o where c.name =:name ")
    Customer findCustomerByName(String name);
}


