package foodOrderApp.services;

import foodOrderApp.entities.Customer;
import foodOrderApp.entities.MenuItem;

import java.util.ArrayList;

public interface AdminServices {
    void addMenuItem(MenuItem menuItem);
    void updateMenuItem(MenuItem menuItem);
    void removeMenuItem(int menuItemId);
    ArrayList<MenuItem> viewAllMenuItems();
    ArrayList<Customer> viewAllCustomers();
}