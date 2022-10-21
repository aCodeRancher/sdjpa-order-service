package guru.springframework.orderservice.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Objects;
import java.util.Set;

@Entity
public class Customer extends BaseEntity{

    @OneToMany (mappedBy = "customer", cascade = CascadeType.PERSIST)
    private Set<OrderHeader> orderHeaderSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(phone, customer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phone);
    }

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<OrderHeader> getOrderHeaderSet() {
        return orderHeaderSet;
    }

     public void setOrderHeaderSet(Set<OrderHeader> orderHeaderSet) {
        this.orderHeaderSet = orderHeaderSet;
    }
}
