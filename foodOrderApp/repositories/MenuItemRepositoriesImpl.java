package foodOrderApp.repositories;

import foodOrderApp.config.Database;
import foodOrderApp.entities.MenuItem;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class MenuItemRepositoriesImpl implements MenuItemRepositories {
    private final Database database;

    public MenuItemRepositoriesImpl(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<MenuItem> getAllMenuItems() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM menu_items";
        ArrayList<MenuItem> menuItems = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setId(resultSet.getInt("id"));
                menuItem.setItemName(resultSet.getString("item_name"));
                menuItem.setCategory(resultSet.getString("category"));
                menuItem.setPrice(resultSet.getInt("price"));
                menuItem.setAvailableQuantity(resultSet.getInt("available_quantity"));
                menuItems.add(menuItem);
            }
        } catch (Exception e) {
            System.out.println("Error fetching menu items: " + e.getMessage());
        }

        return menuItems;
    }

    @Override
    public void addMenuItem(MenuItem menuItem) {
        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO menu_items (item_name, category, price, available_quantity) VALUES (?,  ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, menuItem.getItemName());
            preparedStatement.setString(2, menuItem.getCategory());
            preparedStatement.setInt(3, menuItem.getPrice());
            preparedStatement.setInt(4, menuItem.getAvailableQuantity());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Menu item added successfully!");
            }
        } catch (Exception e) {
            System.out.println("Error adding menu item: " + e.getMessage());
        }
    }

    @Override
    public boolean updateMenuItem(MenuItem menuItem) {
        Connection connection = database.getConnection();
        String sqlStatement = "UPDATE menu_items SET item_name = ?, category = ?, price = ?, available_quantity = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, menuItem.getItemName());
            preparedStatement.setString(2, menuItem.getCategory());
            preparedStatement.setInt(3, menuItem.getPrice());
            preparedStatement.setInt(4, menuItem.getAvailableQuantity());
            preparedStatement.setInt(5, menuItem.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error updating menu item: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeMenuItem(int menuItemId) {
        Connection connection = database.getConnection();
        String sqlStatement = "DELETE FROM menu_items WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, menuItemId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error removing menu item: " + e.getMessage());
            return false;
        }
    }
}