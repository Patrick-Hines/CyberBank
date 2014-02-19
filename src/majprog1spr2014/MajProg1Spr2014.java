/*
 ==================================================================

 Objective:

 This program is a simulation of an automatic teller machine.
 This specific file contains the Main method of the program.

 Developed by: Patrick Hines
 Started:   2-15-2014
 Completed: 2-18-2014

 Developer's Notes:

 ==================================================================
 */
package majprog1spr2014;

import java.io.File;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Patrick Hines
 */
public class MajProg1Spr2014 {

    /*
     ===============================================
     Main Method
     ===============================================
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Create jOptionPane object to prevent scoping issues.
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        chooser.setFileFilter(filter);
        String filePath = "";

        //Assign the proper file name
        if (args == null || args.length == 0) { //Command-line arguments have not been passed.

            int returnVal = chooser.showOpenDialog(null);       //Show the jOptionPane
            File bankDataFile = chooser.getSelectedFile();      //Returns the file object.
            filePath = bankDataFile.getAbsolutePath();   //Get the filepath for latter passing.

        } else { //Command-line arguments have been passed.
            filePath = args[0];
        }

        //Load the bank data
        CyberBank cyberBankObject = new CyberBank();
        cyberBankObject.loadBankData(filePath);

        System.out.println(cyberBankObject.toString());
    }

}

//Thanks for reading.
