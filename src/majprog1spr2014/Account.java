/*
 ==================================================================

 Objective:

 This class handles the different account properties for the
 MajorProgram1 assignment.

 Developed by: Patrick Hines
 Started:   2-15-2014
 Completed: 2-18-2014

 Developer's Notes:

 ==================================================================
 */
package majprog1spr2014;

import java.util.ArrayList;

/**
 *
 * @author Patrick Hines
 */
public class Account {
    /*
     ===============================================
     Class-Level Variables and Objects
     ===============================================
     */

    private double balance;             //This varable will hold the net amount of all transactions.
    private String accountNumber;       //This reference varable will hold the numbers, letters and dashes in the account number.
    private AccountType accountType;    //This reference varable will hold the date of the transaction (enum).
    private ArrayList<Transaction> transactions;     //This reference varable will hold all transaction activity on the account.

    /*
     ===============================================
     Class Constructor
     ===============================================
     */
    public Account() {
        balance = 0;
        accountNumber = "";
        transactions = new ArrayList();

        accountType = AccountType.valueOf("checking");
        /*This is just what I decided as the default transaction type.
         The user will be able to change this, if needed, via the
         setAccountType method.*/

    }


    /*
     ===============================================
     Custom Methods
     ===============================================
     */
    /**
     * @return the number of items in the 'transactions' ArrayList.
     */
    public int getNumTransactions() {
        return transactions.size();
    }

    /**
     * @param index
     * @return the Transaction object at location index in 'transactions'
     * ArrayList.
     */
    public Transaction getTransaction(int index) {
        return transactions.get(index);
    }

    /**
     * @param index
     * @param item
     */
    public void setTransaction(int index, Transaction item) {
        transactions.add(index, item);
    }

    /**
     * @param item
     */
    public void addTransaction(Transaction item) {
        transactions.add(item);
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the accountType
     */
    public String getAccountType() {
        return accountType.name();
    }

    /**
     * @param input
     */
    public void setAccountType(String input) {
        accountType = AccountType.valueOf(input);
    }

    /**
     * @return the String of all data within the class
     */
    @Override
    public String toString() {

        String finalString = balance + System.getProperty("line.separator")
                + accountNumber + System.getProperty("line.separator")
                + accountType.name() + System.getProperty("line.separator");
//                + address + System.getProperty("line.separator")
//                + phoneNumber + System.getProperty("line.separator"); //This two are commented due to formatting issues when saving the data.

        //Iterate through the transactions ArrayList and save its contents to a single string.
        for (Transaction pulledTransaction : transactions) {
            finalString += pulledTransaction.getDate() + System.getProperty("line.separator")
                    + pulledTransaction.getAmount() + System.getProperty("line.separator")
                    + pulledTransaction.getDescription() + System.getProperty("line.separator");

        }
        return finalString;
    }
}
