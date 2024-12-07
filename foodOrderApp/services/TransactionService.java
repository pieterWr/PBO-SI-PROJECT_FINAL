package foodOrderApp.services;

import foodOrderApp.entities.Customer;

public interface TransactionService {
    void placeOrder(Customer customer);
    void editOrder(Customer customer);
    void deleteOrder (Customer customer);

}