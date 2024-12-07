package foodOrderApp.views;

import foodOrderApp.entities.Customer;
import foodOrderApp.entities.MenuItem;
import foodOrderApp.services.AdminServicesImpl;
import foodOrderApp.services.TransactionServiceImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class TerminalView implements MenuView {
    private final AdminServicesImpl adminServicesImpl;
    private final TransactionServiceImpl transactionServiceImpl;
    Scanner input = new Scanner(System.in);

    private Customer currentOrder = new Customer();
    int selectedCourse = -1;

    public TerminalView(AdminServicesImpl adminServicesImpl,

                        TransactionServiceImpl transactionServiceImpl) {
        this.adminServicesImpl = adminServicesImpl;

        this.transactionServiceImpl = transactionServiceImpl;
    }

    @Override
    public void run() {
        mainMenu();
    }

    private void mainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Welcome to the Food Ordering Application\n");
            System.out.println("1. Customer Menu");
            System.out.println("2. Admin Menu");
            System.out.println("3. Exit");
            System.out.print ("Select an option: ");
            int selectedMenu = input.nextInt();
            switch (selectedMenu) {
                case 1:
                    customerMenu();
                    break;
                case 2:
                    adminMenu();
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Please select a valid option.");
            }
        }
    }

    private void customerMenu(){
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Welcome to the Food Ordering Application\n");
            System.out.println("1. Place Order");
            System.out.println("2. Edit Order");
            System.out.println("3. Delete Order");
            System.out.println("4. Exit");
            System.out.print ("Select an option: ");
            int selectedMenu = input.nextInt();
            switch (selectedMenu) {
                case 1:
                    viewAllMenus();
                    placeOrder();
                    break;
                case 2:
                    viewAllMenus();
                    editOrder();
                    break;
                case 3:
                    deleteOrder();
                    break;
                case 4:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Please select a valid option.");
            }
        }
    }


    private void placeOrder() {
        ArrayList<MenuItem> menuItems = adminServicesImpl.viewAllMenuItems();
        Customer customer = new Customer();
        System.out.print("Input Menu Number : ");
        selectedCourse = input.nextInt() -1;
        customer.setOrderMenu(menuItems.get(selectedCourse).getItemName());

        System.out.print("Input Your Name : ");
        input.nextLine();
        customer.setName(input.nextLine());
        System.out.print("Input Your Address : ");
        customer.setAddress(input.nextLine());
        System.out.print("Input Your Phone Number : ");
        customer.setPhoneNumber(input.nextLine());
        currentOrder = customer;

        MenuItem currentMenu = menuItems.get(selectedCourse);
        currentMenu.setAvailableQuantity(currentMenu.getAvailableQuantity() - 1);
        adminServicesImpl.updateMenuItem(currentMenu);

        transactionServiceImpl.placeOrder(customer);
    }

    private void editOrder() {
        if (currentOrder.getOrderMenu() != null){
            ArrayList<MenuItem> menuItems = adminServicesImpl.viewAllMenuItems();
            MenuItem currentMenu = menuItems.get(selectedCourse);
            currentMenu.setAvailableQuantity(currentMenu.getAvailableQuantity() + 1);
            adminServicesImpl.updateMenuItem(currentMenu);

            System.out.print("Input Menu Number : ");
            selectedCourse = input.nextInt() -1;
            currentOrder.setOrderMenu(menuItems.get(selectedCourse).getItemName());

            System.out.print("Input Your Name : ");
            input.nextLine();
            currentOrder.setName(input.nextLine());
            System.out.print("Input Your Address : ");
            currentOrder.setAddress(input.nextLine());
            System.out.print("Input Your Phone Number : ");
            currentOrder.setPhoneNumber(input.nextLine());
            transactionServiceImpl.editOrder(currentOrder);


            currentMenu = menuItems.get(selectedCourse);
            currentMenu.setAvailableQuantity(currentMenu.getAvailableQuantity() - 1);
            adminServicesImpl.updateMenuItem(currentMenu);



        }else {
            System.out.println("You Haven't Place an Order Yet !");
        }
    }
    private void deleteOrder() {
        if (currentOrder.getOrderMenu() != null){
            ArrayList<MenuItem> menus = adminServicesImpl.viewAllMenuItems();
            MenuItem currentMenu = menus.get(selectedCourse);
            currentMenu.setAvailableQuantity(currentMenu.getAvailableQuantity() + 1);
            adminServicesImpl.updateMenuItem(currentMenu);

            transactionServiceImpl.deleteOrder(currentOrder);
            currentOrder = new Customer();
            System.out.println("Successfully Deleted");
        }else {
            System.out.println("You Haven't Place an Order Yet !");
        }
    }




    private void adminMenu() {
        boolean isAdminMenuRunning = true;
        while (isAdminMenuRunning) {
            System.out.println("Welcome to the Admin Menu\n");
            System.out.println("1. View All Customers");
            System.out.println("2. View All Menus");
            System.out.println("3. Add Menu Item");
            System.out.println("4. Update Menu Item");
            System.out.println("5. Remove Menu Item");
            System.out.println("6. Exit to Main Menu");
            System.out.print("Select an option: ");
            int selectedOption = input.nextInt();

            switch (selectedOption) {
                case 1:
                    viewAllCustomers();
                    break;
                case 2:
                    viewAllMenus();
                    break;
                case 3:
                    addMenuItem();
                    break;
                case 4:
                    viewAllMenus();
                    updateMenuItem();
                    break;
                case 5:
                    viewAllMenus();
                    removeMenuItem();
                    break;
                case 6:
                    isAdminMenuRunning = false;
                    break;
                default:
                    System.out.println("Please select a valid option.");
            }
        }
    }

    private void addMenuItem() {
        MenuItem menuItem = new MenuItem();
        System.out.print("Input Menu Name : ");
        input.nextLine();
        menuItem.setItemName(input.nextLine());
        System.out.print("Input Menu Category : ");
        menuItem.setCategory(input.nextLine());
        System.out.print("Input Menu Price : ");
        menuItem.setPrice(input.nextInt());
        System.out.print("Input Menu Quantity : ");
        menuItem.setAvailableQuantity(input.nextInt());

        adminServicesImpl.addMenuItem(menuItem);
    }

    private void updateMenuItem() {
        ArrayList<MenuItem> menus = adminServicesImpl.viewAllMenuItems();
        System.out.print("Input Menu Number : ");
        MenuItem menuItem = menus.get(input.nextInt() -1);
        System.out.print("Input Menu Name : ");
        input.nextLine();
        menuItem.setItemName(input.nextLine());
        System.out.print("Input Menu Category : ");
        menuItem.setCategory(input.nextLine());
        System.out.print("Input Menu Price : ");
        menuItem.setPrice(input.nextInt());
        System.out.print("Input Menu Quantity : ");
        menuItem.setAvailableQuantity(input.nextInt());

        adminServicesImpl.updateMenuItem(menuItem);
    }

    private void removeMenuItem() {
        ArrayList<MenuItem> menus = adminServicesImpl.viewAllMenuItems();
        System.out.print("Input Menu Number : ");
        MenuItem menuItem = menus.get(input.nextInt() -1);

        adminServicesImpl.removeMenuItem(menuItem.getId());
    }

    private void viewAllCustomers() {
        ArrayList<Customer> customers = adminServicesImpl.viewAllCustomers();
        if (!customers.isEmpty()){
            int nums = 1;
            System.out.println("No\tName\tPhoneNumber\tAddress\torderMenu");
            for (Customer customer : customers) {
                System.out.println(nums + ".\t" + customer.getName() + "\t" + customer.getPhoneNumber() + "\t" + customer.getAddress() + "\t" + customer.getOrderMenu() + "\t");
                nums++;
            }
        }else {
            System.out.println("There Haven't Any Customer Order Yet");
        }
    }

    private void viewAllMenus() {
        ArrayList<MenuItem> menuItems = adminServicesImpl.viewAllMenuItems();
        if (!menuItems.isEmpty()){
            int nums = 1;
            System.out.println("No\tName\tCategory\tPrice\tQuantity");
            for (MenuItem  item : menuItems) {
                System.out.println(nums + ".\t" + item.getItemName() + "\t" + item.getCategory() + "\t" + item.getPrice() + "\t" + item.getAvailableQuantity() );
                nums++;
            }
        }else {
            System.out.println("There Haven't Any Menu Added Yet");
        }
    }


}