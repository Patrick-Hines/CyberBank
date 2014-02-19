
/*
 ==================================================================

 Objective:

 This class handles the different bank properties for the
 MajorProgram1 assignment.

 Developed by: Patrick Hines
 Started:   2-15-2014
 Completed: 2-18-2014

 Developer's Notes:

 ==================================================================
 */
package majprog1spr2014;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Patrick Hines
 */
public class CyberBank {
    /*
     ===============================================
     Class-Level Variables and Objects
     ===============================================
     */

    private String bankName;                //This reference variable will hold the name of the bank.
    private String address;                 //This reference variable will hold the mailing address of the bank.
    private String phoneNumber;             //This reference variable will hold the phone number of the bank.
    private double bankBalance;             //This reference variable will hold the sum balance of all bank customers.
    private ArrayList<Customer> customers;  //This ArrayList holds all customers of the bank (as objects)
    /*
     ===============================================
     Class Constructor
     ===============================================
     */

    //Description
    public CyberBank() {

        bankName = "";
        address = "";
        phoneNumber = "";
        bankBalance = 0;
        customers = new ArrayList();

    }

    /*
     ===============================================
     Custom Methods
     ===============================================
     */
    /**
     * @return the number of items in the 'customers' ArrayList.
     */
    public int getNumCustomers() {
        return customers.size();
    }

    /**
     * @param index
     * @return the Customer object at location index in 'customers' ArrayList.
     */
    public Customer getCustomer(int index) {
        return customers.get(index);
    }

    /**
     * @param index
     * @param item
     */
    public void setCustomer(int index, Customer item) {
        customers.add(index, item);
    }

    /**
     * @param item
     */
    public void addCustomer(Customer item) {
        customers.add(item);
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
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
     * @return the bankBalance
     */
    public double getBankBalance() {
        return bankBalance;
    }

    /**
     * @param bankBalance the bankBalance to set
     */
    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }

    /**
     * @param custId
     * @param pin
     * @return customer object or null if invalid credentials/not found
     */
    public Customer authenticateCustomer(int custId, int pin) {

        for (Customer pulledCustomer : customers) { //Begin iteration
            int pulledCustomerID = pulledCustomer.getCustomerId(); //Grab the customer's ID

            if (pulledCustomerID == custId) { //Determine if said ID matches a customer
                int pulledCustomerPin = pulledCustomer.getPin(); //If so, grab their PIN number and save it

                if (pulledCustomerPin == pin) { //If that PIN number is the same as the one passed via arguments, then a match is found.
                    return pulledCustomer;
                }
            }
        }

        return null; //If we can't find anything, simply return nothing.

    }

    /**
     * @param inputFileName
     */
    public void loadBankData(String inputFileName) {
        try {
            //Create all needed objects for using methods.
            File data = new File(inputFileName); //Create file object
            Scanner dataParse = new Scanner(data);  //Create Scanner object to being parsing

            //Grab constant data
            bankName = dataParse.nextLine();
//            address = dataParse.nextLine();
//            phoneNumber = dataParse.nextLine(); //These two are removed due to supplied formatting issues.

            //Being 'while' loop to iterate through the file. Make use of the Setter methods.
            while (dataParse.hasNextLine()) {

                Customer customerObject = new Customer();

                customerObject.setFirstName(dataParse.nextLine());
                customerObject.setLastName(dataParse.nextLine());
                customerObject.setCustomerId(Integer.parseInt(dataParse.nextLine()));
                customerObject.setDob(dataParse.nextLine());
                customerObject.setAddress(dataParse.nextLine());
                customerObject.setPhoneNumber(dataParse.nextLine());
                customerObject.setPin(Integer.parseInt(dataParse.nextLine()));

                int numberOfAccounts = Integer.parseInt(dataParse.nextLine());

                accountLoop:
                for (int i = 0; i < (numberOfAccounts); i++) {
                    double totalAccountBalance = 0.0; //This is to hold the sum value of all read transactions.

                    //Create new Account object
                    Account accountObject = new Account();

                    accountObject.setAccountType(dataParse.nextLine());
                    accountObject.setAccountNumber(dataParse.nextLine());
                    dataParse.nextLine(); //This line reads in the value of the account balance. (This is here just to waste a line)

                    int numberOfTransactions = Integer.parseInt(dataParse.nextLine()); //Number of Transactions

                    //Begin adding any extra values to the arrays
                    if (numberOfTransactions >= 1) {

                        for (int j = 0; j < numberOfTransactions; j++) {

                            //Create new Transaction object
                            Transaction transactionObject = new Transaction();

                            //Log the further number of transactions
                            transactionObject.setTransactionType(dataParse.nextLine());
                            transactionObject.setDate(dataParse.nextLine());
                            transactionObject.setAmount(Double.parseDouble(dataParse.nextLine()));
                            transactionObject.setDescription(dataParse.nextLine());

                            //Calculate the current account balance
                            totalAccountBalance += transactionObject.getAmount();

                            //Save the new transaction again
                            accountObject.addTransaction(transactionObject);

                        }

                    }

                    //Save the account balance
                    accountObject.setBalance(totalAccountBalance);

                    //Add to customer's account array
                    customerObject.addAccount(accountObject);

                    totalAccountBalance = 0;

                }

                customers.add(customerObject);

            } //Sigifies the end of a customer's data

        } catch (FileNotFoundException ex) {
            showMessageDialog(null, "Sorry, I could not locate this file.");
        }
    }

    /**
     * @param inputFileName
     */
    public void saveBankData(String inputFileName) {
        PrintWriter writer = null;
        try {
            File data = new File(inputFileName);
            writer = new PrintWriter(data);
            writer.print(this.toString());
        } catch (FileNotFoundException ex) {
            showMessageDialog(null, "Sorry, I could not locate this file.");
        } finally {
            writer.close();
        }
    }

    /**
     * @return the String of all data within the class
     */
    @Override
    public String toString() {

        String finalString = bankName + System.getProperty("line.separator");
//                + address + System.getProperty("line.separator")
//                + phoneNumber + System.getProperty("line.separator"); //This two are commented due to formatting issues when saving the data.

        //Iterate through the Customer ArrayList and save its contents to a single string.
        for (Customer pulledCustomer : customers) {
            finalString += pulledCustomer.getFirstName() + System.getProperty("line.separator")
                    + pulledCustomer.getLastName() + System.getProperty("line.separator")
                    + pulledCustomer.getCustomerId() + System.getProperty("line.separator")
                    + pulledCustomer.getDob() + System.getProperty("line.separator")
                    + pulledCustomer.getAddress() + System.getProperty("line.separator")
                    + pulledCustomer.getPhoneNumber() + System.getProperty("line.separator")
                    + pulledCustomer.getPin() + System.getProperty("line.separator")
                    + pulledCustomer.getNumAccounts() + System.getProperty("line.separator");

            //Pull the account information on the customer
            for (int i = 0; i < pulledCustomer.getNumAccounts(); i++) {
                Account pulledAccount = pulledCustomer.getAccount(i);
                finalString += pulledAccount.getAccountType() + System.getProperty("line.separator")
                        + pulledAccount.getAccountNumber() + System.getProperty("line.separator")
                        + pulledAccount.getBalance() + System.getProperty("line.separator")
                        + pulledAccount.getNumTransactions() + System.getProperty("line.separator");

                //Pull the transaction information on the customer
                for (int j = 0; j < pulledAccount.getNumTransactions(); j++) {
                    Transaction pulledTransaction = pulledAccount.getTransaction(j);
                    finalString += pulledTransaction.getTransactionType() + System.getProperty("line.separator")
                            + pulledTransaction.getDate() + System.getProperty("line.separator")
                            + pulledTransaction.getAmount() + System.getProperty("line.separator")
                            + pulledTransaction.getDescription() + System.getProperty("line.separator");
                }
            }

        }

        return finalString;
    }

}
