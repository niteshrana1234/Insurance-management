package com.takeo.main;

import com.takeo.model.Address;
import com.takeo.model.Customer;

import java.util.*;

import static com.takeo.main.PolicyManagementModule.scanner;

public class CustomerManagementModule {

    /**
     * Map with key as Integer, Value as Customer
     */
    public static Map<String, Customer> customerList = new HashMap<>();

    /**
     * This method takes Customer object and put in map with id as key
     * @param customer
     */
    public void saveCustomer(Customer customer) {
        customerList.put(customer.getCustomerId(), customer);
        System.out.println("");
        System.out.println("Account Registered Successfully !!!");
        System.out.println("Name : " + customer.getFullName() + ", Customer ID : " + customer.getCustomerId());
    }

    /**
     * This method take user input and create customer
     * @return
     */
    public Customer customerInput() {
        CustomerManagementModule custom = new CustomerManagementModule();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\u001B[4mPersonal information:\u001B[0m ");
        System.out.print("Enter full name:");
        String name = scanner.nextLine();
        System.out.print("Enter phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter date of birth(DDMMYYYY):");
        String dateOfBirth = scanner.nextLine();
        System.out.print("Enter email address:");
        String emailAddress = scanner.nextLine();
        Address address = custom.addressInput();
        System.out.print("Set password:");
        String password = scanner.nextLine();
        return new Customer(name, phoneNumber, emailAddress,dateOfBirth, address, password);

    }

    /**
     *This method takes user input for address
     * @return
     */
    public Address addressInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("********************************************");
        System.out.println("\u001B[4mAddress:\u001B[0m ");
        System.out.print("Building number:");
        int buildingNum = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Street:");
        String street = scanner.nextLine();
        System.out.print("zip code:");
        int zipCode = scanner.nextInt();
        scanner.nextLine();
        System.out.print("city:");
        String city = scanner.nextLine();
        System.out.print("state:");
        String state = scanner.nextLine();
        System.out.print("country:");
        String country = scanner.nextLine();
        Address address = new Address(buildingNum, street, state, zipCode, city, country);
        return address;
    }

    /**
     * This method removes customer from hashmap
     */
    public void removeCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Customer Id:");
        int input = scanner.nextInt();
        if (verifyCustomer(input)) {
            Customer selectedCustomer = customerList.get(input);
            customerList.remove(input);
            System.out.println(selectedCustomer.getFullName() + " has been removed from the list!!");
        } else {
            System.out.println("Customer ID not found");
        }

    }

    /**
     * This method allows to edit customer personal information
     */
    public void modifyCustomerInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter Customer ID:");
        int input = scanner.nextInt();
        if (verifyCustomer(input)) {
            Customer selectClient = customerList.get(input);
            scanner.nextLine();
            System.out.println("\u001B[4mChange Of Information\u001B[0m ");
            System.out.print("Enter full name:");
            String name = scanner.nextLine();
            selectClient.setFullName(name);
            System.out.print("Enter phone number:");
            String phoneNumber = scanner.nextLine();
            selectClient.setPhoneNumber(phoneNumber);
            System.out.print("Enter date of birth(DDMMYYYY):");
            String dateOfBirth = scanner.nextLine();
            selectClient.setDateOfBirth(dateOfBirth);
            System.out.print("Enter email address:");
            String emailAddress = scanner.nextLine();
            selectClient.setEmailAddress(emailAddress);
            Address address = addressInput();
            selectClient.setAddress(address);
        } else {
            System.out.println("No matched ID found");
        }

    }

    /**
     * This method retrieve details of customer
     */
    public void customerDetails() {
        System.out.print("Enter id:");
        String id = scanner.next();
        Customer customer = customerList.get(id);
        String formattedText = "\u001B[4m" + customer.getFullName() +"\u001B[0m ";
        System.out.println("\n"+formattedText+ "\nAddress:" + customer.getAddress()+"\nEmail : " + customer.getEmailAddress() + "\nDate of birth(MMDDYYY) : " + customer.getDateOfBirth() + "\nContact :" + customer.getPhoneNumber());
    }

    /**
     * This method takes int in parameter and checks if id exist in map
     * @param id
     * @return
     */
    public Boolean verifyCustomer(int id) {
        boolean check = false;
        if (customerList.containsKey(id)) {
            return true;
        }
        return check;
    }

    /**
     * This method allows user to enter id and password
     * and grants customer menu only if id and password match
     * Each customer has unique id and password
     * @return
     */
    public Boolean customerLogin() {
        System.out.print("\nEnter id:");
        String id = scanner.next();
        System.out.print("Enter password:");
        String pass = scanner.next();
        Customer customer = customerList.get(id);
        if (customer != null) {
            if (customer.getCustomerId() == id && customer.getPassword().equals(pass)) {
                System.out.println("\n"+"Login successful !!!");
                return true;
            }
        }
        return false;
    }


}
