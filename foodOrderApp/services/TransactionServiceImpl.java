package foodOrderApp.services;

import foodOrderApp.entities.Customer;
import foodOrderApp.repositories.CustomerRepositories;
import org.springframework.stereotype.Component;

@Component
public class TransactionServiceImpl implements TransactionService {
    private  CustomerRepositories customerRepositories;

    public TransactionServiceImpl(CustomerRepositories customerRepositories) {
        this.customerRepositories = customerRepositories;
    }

    @Override
    public void placeOrder(Customer customer) {
        customerRepositories.addCustomer(customer);
    }

    @Override
    public void editOrder(Customer customer) {
        customerRepositories.updateCustomerOrder(customer);
    }

    @Override
    public void deleteOrder(Customer customer) {
        customerRepositories.deleteCustomerOrder(customer);
    }
}