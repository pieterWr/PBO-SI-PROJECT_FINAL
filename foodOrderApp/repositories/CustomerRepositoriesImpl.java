package foodOrderApp.repositories;

import foodOrderApp.config.Database;
import foodOrderApp.entities.Customer;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class CustomerRepositoriesImpl implements CustomerRepositories {
    private final Database database;

    public CustomerRepositoriesImpl(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM customers";
        ArrayList<Customer> customerList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setAddress(resultSet.getString("address"));
                customer.setOrderMenu(resultSet.getString("orderMenu"));
                customerList.add(customer);
            }
        } catch (Exception e) {
            System.out.println("Error fetching customers: " + e.getMessage());
        }

        return customerList;
    }

    @Override
    public ArrayList<Customer> getCustomerList() {
        return getAllCustomers();
    }

    @Override
    public void addCustomer(Customer customer) {
        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO customers (name, orderMenu, phone_number, address) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getOrderMenu());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getAddress());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer added successfully!");
            }
        } catch (Exception e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }

    @Override
    public boolean updateCustomerOrder(Customer customer) {
        Connection connection = database.getConnection();
        String sqlStatement = "UPDATE customers SET name = ?, orderMenu = ?, phone_number = ?, address = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getOrderMenu());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setInt(5, customer.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error updating customer profile: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteCustomerOrder(Customer customer) {
        Connection connection = database.getConnection();
        String sqlStatement = "DELETE FROM customers WHERE name = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, customer.getName());


            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error updating customer profile: " + e.getMessage());
            return false;
        }
    }

}