package foodOrderApp.entities;

public class Customer {
    private String name;
    private String phoneNumber;
    private String address;
    private String orderMenu;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter for orderMenu
    public String getOrderMenu() {
        return orderMenu;
    }

    // Setter for orderMenu
    public void setOrderMenu(String orderMenu) {
        this.orderMenu = orderMenu;
    }

    // Other getters and setters for existing attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}