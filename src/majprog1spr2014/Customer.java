/*
 ==================================================================

 Objective:

 This class handles the different customer properties for the
 MajorProgram1 assignment.

 Developed by: Patrick Hines
 Started:   2-15-2014
 Completed: 2-18-2014

 Developer's Notes:

 ==================================================================
 */
package majprog1spr2014;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Patrick Hines
 */
public class Customer {

    private String firstName;   //This reference varable will hold the customer's first name.
    private String lastName;    //This reference varable will hold the customer's last name.
    private String address;     //This reference varable will hold the customer's mailing address.
    private String phoneNumber; //This reference varable will hold the customer's phone number.
    private int customerId;     //This varable will hold the customer's unique ID number (cannot start with zero)
    private int pin;            //This varable will hold the customer's personal identification number at the ATM.

    private Date dob; //This reference variable will hold the Date of Birth.

    private ArrayList<Account> accounts; //This reference variable will hold all the customer accounts at the bank.

    /*
     ===============================================
     Class Constructor
     ===============================================
     */
    public Customer() {
        firstName = "";
        lastName = "";
        address = "";
        phoneNumber = "";

        customerId = 1;
        pin = 0;

        dob = new Date();

        accounts = new ArrayList();
    }

    /*
     ===============================================
     Custom Methods
     ===============================================
     */
    /**
     * @return converted Date object (String -> Date)
     */
    private Date stringToDate(String sDate) {

        DateFormat df = new SimpleDateFormat("mm/DD/yyyy");

        try {
            return df.parse(sDate);
        } catch (java.text.ParseException ex) {
            System.err.println("Could not convert input date to Date object: " + sDate);
            System.exit(-1);
        }
        return null;
    }

    /**
     * @return converted String object (Date -> String)
     */
    private String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat("mm/DD/yyyy");
        return df.format(date);
    }

    /**
     * @param input
     */
    public void setDob(String input) {
        dob = stringToDate(input);
    }

    /**
     * @return the number of items in the 'accounts' ArrayList.
     */
    public String getDob() {
        return this.dateToString(dob);
    }

    /**
     * @return the number of items in the 'accounts' ArrayList.
     */
    public int getNumAccounts() {
        return accounts.size();
    }

    /**
     * @param index
     * @return the Account object at location index in 'accounts' ArrayList.
     */
    public Account getAccount(int index) {
        return accounts.get(index);
    }

    /**
     * @param index
     * @param item
     */
    public void setAccount(int index, Account item) {
        accounts.add(index, item);
    }

    /**
     * @param item
     */
    public void addAccount(Account item) {
        accounts.add(item);
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the pin
     */
    public int getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(int pin) {
        this.pin = pin;
    }

    /**
     * @return the String of all data within the class
     */
    @Override
    public String toString() {

        String finalString = firstName + System.getProperty("line.separator")
                + lastName + System.getProperty("line.separator")
                + address + System.getProperty("line.separator")
                + phoneNumber + System.getProperty("line.separator")
                + customerId + System.getProperty("line.separator")
                + pin + System.getProperty("line.separator")
                + this.getDob() + System.getProperty("line.separator");

        //Iterate through the accounts ArrayList and save its contents to a single string.
        for (Account pulledAccount : accounts) {
            finalString += pulledAccount.getBalance() + System.getProperty("line.separator")
                    + pulledAccount.getAccountNumber() + System.getProperty("line.separator")
                    + pulledAccount.getAccountType() + System.getProperty("line.separator");

        }

        return finalString;
    }

}
