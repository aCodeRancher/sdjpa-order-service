package guru.springframework.orderservice;

import guru.springframework.orderservice.domain.Address;
import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.repositories.CustomerRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerValidationTest {

    @Autowired
    CustomerRepository customerRepository;

    private String street = "100 M Street";
    private String city = "Arlington";
    private String state = "VA";
    private String zipCode = "22201";
    private String email = "test@gmail.com";
    private String phone = "7031231234";
    private String name = "Tester Tester";
    private String invalidEmail = "test.com";
    private String invalidEmailLength = "@m.ai";
    //State name has length > 30
    private String invalidState = "01234567890123456789012345678901";
    private String invalidZip = "01234567890123456789012345678901";
    private String invalidCity = "01234567890123456789012345678901";
    private String invalidStreet = "01234567890123456789012345678901";
    private String invalidPhone = "0123456789012345678901";

    @Test
    void emailValidity(){
         Customer customer = new Customer();
         customer.setCustomerName(name);
         customer.setPhone(phone);
         customer.setEmail(invalidEmail);
         Address address = new Address();
         address.setAddress(street);
         address.setCity(city);
         address.setState(state);
         address.setZipCode(zipCode);
         customer.setAddress(address);
         assertThrows(ConstraintViolationException.class, ()->customerRepository.save(customer));

         customer.setEmail(email);
         Customer customerSaved = customerRepository.save(customer);
         assertNotNull(customerSaved);
    }

    @Test
    void emailLengthValidity(){
        Customer customer = new Customer();
        customer.setCustomerName(name);
        customer.setPhone(phone);
        customer.setEmail(invalidEmailLength);
        Address address = new Address();
        address.setAddress(street);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);
        customer.setAddress(address);
        assertThrows(ConstraintViolationException.class, ()->customerRepository.save(customer));

        customer.setEmail(email);
        Customer customerSaved = customerRepository.save(customer);
        assertNotNull(customerSaved);
    }

    @Test
    void phoneValidity(){
        Customer customer = new Customer();
        customer.setCustomerName(name);
        customer.setPhone(invalidPhone);
        customer.setEmail(email);
        Address address = new Address();
        address.setAddress(street);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);
        customer.setAddress(address);
        assertThrows(ConstraintViolationException.class, ()->customerRepository.save(customer));

        customer.setPhone(phone);
        Customer customerSaved = customerRepository.save(customer);
        assertNotNull(customerSaved);
    }
    @Test
    void stateValidity(){

        Customer customer = new Customer();
        customer.setCustomerName(name);
        customer.setPhone(phone);
        customer.setEmail(email);
        Address address = new Address();
        address.setAddress(street);
        address.setCity(city);
        address.setState(invalidState);
        address.setZipCode(zipCode);
        customer.setAddress(address);
        assertThrows(ConstraintViolationException.class, ()->customerRepository.save(customer));

        address.setState(state);
        Customer customerSaved = customerRepository.save(customer);
        assertNotNull(customerSaved);
    }

    @Test
    void zipValidity(){
        Customer customer = new Customer();
        customer.setCustomerName(name);
        customer.setPhone(phone);
        customer.setEmail(email);
        Address address = new Address();
        address.setAddress(street);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(invalidZip);
        customer.setAddress(address);
        assertThrows(ConstraintViolationException.class, ()->customerRepository.save(customer));

        address.setZipCode(zipCode);
        Customer customerSaved = customerRepository.save(customer);
        assertNotNull(customerSaved);
    }

    @Test
    void cityValidity(){
        Customer customer = new Customer();
        customer.setCustomerName(name);
        customer.setPhone(phone);
        customer.setEmail(email);
        Address address = new Address();
        address.setAddress(street);
        address.setCity(invalidCity);
        address.setState(state);
        address.setZipCode(zipCode);
        customer.setAddress(address);
        assertThrows(ConstraintViolationException.class, ()->customerRepository.save(customer));

        address.setCity(city);
        Customer customerSaved = customerRepository.save(customer);
        assertNotNull(customerSaved);
    }

    @Test
    void addressValidity(){
        Customer customer = new Customer();
        customer.setCustomerName(name);
        customer.setPhone(phone);
        customer.setEmail(email);
        Address address = new Address();
        address.setAddress(invalidStreet);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);
        customer.setAddress(address);
        assertThrows(ConstraintViolationException.class, ()->customerRepository.save(customer));

        address.setAddress(street);
        Customer customerSaved = customerRepository.save(customer);
        assertNotNull(customerSaved);
    }

    @Test
    void nameValidity(){
        Customer customer = new Customer();
        Address address = new Address();
        //address length > 30
        address.setAddress("012345678901234567890123456789123");
        customer.setAddress(address);
        assertThrows(ConstraintViolationException.class, ()->customerRepository.save(customer));

        address.setAddress("100 M Street");
        customer.setAddress(address);
        Customer customerSaved = customerRepository.save(customer);
        assertNotNull(customerSaved);
    }
}
