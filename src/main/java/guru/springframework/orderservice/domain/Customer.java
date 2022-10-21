package guru.springframework.orderservice.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@AttributeOverrides({
        @AttributeOverride(
                name = "address.address",
                column = @Column(name = "address")
        ),
        @AttributeOverride(
                name = "address.city",
                column = @Column(name = "city")
        ),
        @AttributeOverride(
                name = "address.state",
                column = @Column(name = "state")
        ),
        @AttributeOverride(
                name = "address.zipCode",
                column = @Column(name = "zip_code")
        )
})
public class Customer extends BaseEntity{

    @OneToMany (mappedBy = "customer", cascade = CascadeType.PERSIST)
    private Set<OrderHeader> orderHeaderSet;

    private String phone;

    private String name;

    private String email;

    @Embedded
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        return result;
    }


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
