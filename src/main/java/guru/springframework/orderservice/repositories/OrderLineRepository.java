package guru.springframework.orderservice.repositories;

import guru.springframework.orderservice.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
