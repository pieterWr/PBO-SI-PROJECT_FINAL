package foodOrderApp.repositories;

import foodOrderApp.entities.Customer;
import java.util.ArrayList;

public interface CustomerRepositories {
    boolean deleteCustomerOrder(Customer customer);
    boolean updateCustomerOrder(Customer customer);
    void addCustomer(Customer customer);
    ArrayList<Customer> getCustomerList();
    ArrayList<Customer> getAllCustomers();
}