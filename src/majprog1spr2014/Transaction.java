/*
 ==================================================================

 Objective:

 This class handles the different transaction properties for the
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
import java.util.Date;

/**
 *
 * @author Patrick Hines
 */
public class Transaction {
    /*
     ===============================================
     Class-Level Variables and Objects
     ===============================================
     */

    private Date date;              //This reference varable will hold the date of the transaction.
    private double amount;          //This variable will hold the amount of money in the transaction (Unit: USD).
    private String description;     //This reference variable will hold the description of the transaction.
    private TransactionType transactionType; //This reference variable will hold the enumerated data type of the transaction.

    /*
     ===============================================
     Class Constructor
     ===============================================
     */
    public Transaction() {
        date = new Date();
        amount = 0;
        description = "";

        transactionType = TransactionType.valueOf("credit");
        /*This is just what I decided as the default transaction type.
         The user will be able to change this, if needed, via the
         setTranscationType method.*/

    }

    /*
     ===============================================
     Custom Methods
     ===============================================
     */
    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the transactionType
     */
    public String getTransactionType() {
        return transactionType.name();
    }

    /**
     * @param input
     */
    public void setTransactionType(String input) {
        transactionType = TransactionType.valueOf(input);
    }

    /**
     * @return the current Date as String
     */
    private String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat("mm/DD/yyyy");
        return df.format(date);
    }

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
     * @return the current Date as String
     */
    public String getDate() {
        return dateToString(date);
    }

    /**
     * @param input
     */
    public void setDate(String input) {
        date = stringToDate(input);
    }

    /**
     * @return the String of all data within the class
     */
    @Override
    public String toString() {
        return description + System.getProperty("line.separator")
                + dateToString(date) + System.getProperty("line.separator")
                + transactionType + System.getProperty("line.separator")
                + amount;
    }
}
