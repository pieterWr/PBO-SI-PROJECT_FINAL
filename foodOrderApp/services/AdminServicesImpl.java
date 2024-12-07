package foodOrderApp.services;

import foodOrderApp.entities.Customer;
import foodOrderApp.entities.MenuItem;
import foodOrderApp.repositories.CustomerRepositories;
import foodOrderApp.repositories.MenuItemRepositories;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AdminServicesImpl implements AdminServices {
    private final MenuItemRepositories menuItemRepositories;
    private final CustomerRepositories customerRepositories;

    public AdminServicesImpl(MenuItemRepositories menuItemRepositories,
                             CustomerRepositories customerRepositories) {
        this.menuItemRepositories = menuItemRepositories;
        this.customerRepositories = customerRepositories;
    }

    @Override
    public void addMenuItem(MenuItem menuItem) {
        menuItemRepositories.addMenuItem(menuItem);
    }

    @Override
    public void updateMenuItem(MenuItem menuItem) {
        menuItemRepositories.updateMenuItem(menuItem);
    }

    @Override
    public void removeMenuItem(int menuItemId) {
        menuItemRepositories.removeMenuItem(menuItemId);
    }

    @Override
    public ArrayList<MenuItem> viewAllMenuItems() {
        return menuItemRepositories.getAllMenuItems();
    }

    @Override
    public ArrayList<Customer> viewAllCustomers() {
        return customerRepositories.getAllCustomers();
    }
}