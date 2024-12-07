package foodOrderApp.repositories;

import foodOrderApp.entities.MenuItem;
import java.util.ArrayList;

public interface MenuItemRepositories {
    ArrayList<MenuItem> getAllMenuItems();
    void addMenuItem(MenuItem menuItem);
    boolean updateMenuItem(MenuItem menuItem);
    boolean removeMenuItem(int menuItemId);
}