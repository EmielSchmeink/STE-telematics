package CANParser.canparser.src.main.java.com.ste; 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.opencsv.*;

/**
 * The supplied default format files consists of multiple fields. 
 * 
 * These fields are seperated by semicolons but also by quotes and curly brackets. We have to be careful, 
 * as a certain field can contain commas within this field, to denote the states a field can have. 
 * Therefore we split the lines into their respective fields by using the semicolon as a delimiter, but we also have 
 * to split some fields further into individual states. 
 * 
 */
public class CANParser {

    // Variable that has to be set in order to use a filepicker when browsing for a format file
    static final boolean USE_FILEPICKER = false; 

    public List<ParsedMessage> parseMessagesDefault() {
        // Define our CSVReader variable which will be used to read a CAN_overview.csv file
        CSVReader reader = null; 

        // Define the ArrayList which will store the lines in the String format
        List<List<String>> arrayOfLines = null; 

        try { 
            // THe default path where the format file resides. May be overridden if the filechooser is used
            String filename = "canparser\\src\\res\\CAN_overview_2019_17-format.csv";
            
            // Read in the filename depending on whether we want to choose a file or just use the default one
            if (USE_FILEPICKER) {
                // Set up the filechooser 
                JFileChooser jfc = new JFileChooser();
                
                jfc.setDialogTitle("Import CAN messages format file");
                jfc.setApproveButtonText("Import format");
                
                // Restrict file extensions to the .csv extension
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
                jfc.setFileFilter(filter);

                // Open the dialog
                int returnVal;
                boolean bExit = false; 

                do {
                    
                    returnVal = jfc.showOpenDialog(null);

                    if (returnVal == JFileChooser.CANCEL_OPTION) {
                        System.out.println("Cancel pressed");
                    } else if (returnVal == JFileChooser.ERROR_OPTION) {
                        System.out.println("Error occurred");
                    } else if (returnVal == JFileChooser.APPROVE_OPTION) {
                        // A viable file was chosen by the user, save it as the filename
                        filename = jfc.getSelectedFile().getAbsolutePath();
                        
                        // We can now exit the filechooser loop
                        bExit = true; 
                    }
                    
                } while(!bExit);

                
            } 
            // Instantiate a CSVReader object and use it to read the default messages file 
            reader = new CSVReaderBuilder(
                new FileReader(filename))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build();
            
            // Create two variables that will hold our csv lines. 
            arrayOfLines = new ArrayList<>();
            String[] line; 
            // Now that we have a messages file read in, read the lines one by one and store them in the arraylist
            // We discard the first line and the second line, which is a Version line followed by a header line
            line = reader.readNext();
            line = reader.readNext();

            while ((line = reader.readNext()) != null) {

                arrayOfLines.add(Arrays.asList(line));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Use the list of parsedMessages to create multiple ParsedMessage objects
        List<ParsedMessage> parsedMessages = new ArrayList<>(); 

        for (List<String> line : arrayOfLines) {
            ParsedMessage pm = new ParsedMessage(
            line.get(0), line.get(1), line.get(2), line.get(3),
            line.get(4), line.get(5), line.get(6), line.get(7),
            line.get(8), line.get(9), line.get(10),line.get(11), 
            line.get(12), line.get(13), line.get(14), line.get(15));

            parsedMessages.add(pm);
        }

        return parsedMessages;
    }

    public List<ParsedTypedef> parseTypedefsDefault() {
        // Define our CSVReader variable, which will be used to read in the typedefs file. 
        CSVReader reader = null;
        // Define the ArrayList which will store the lines in a String format
        List<List<String>> arrayOfLines = null;

        try { 
            // Instantiate a CSVReader object and use it to read the default typedefs file
            reader = new CSVReaderBuilder(
                new FileReader("canparser\\src\\res\\CAN_typedef_2019_17-format.csv"))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build();

            // Create two variables that will hold our csv lines. The arraylist will store the entries for future use
            arrayOfLines = new ArrayList<>();
            String[] line; 
            
            // Now that we have a typedefs file read in, read the lines one by one and store them in the arraylist
            // We discard the first line (header line)
            line = reader.readNext();

            while ((line = reader.readNext()) != null) {

                arrayOfLines.add(Arrays.asList(line));

            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Now that we have a populated list of typedef strings, we store them in 
        // ParsedTypedef classes to allow easier access 
        List<ParsedTypedef> parsedTypedefs = new ArrayList<>(); 
        for (List<String> ls : arrayOfLines) {
            ParsedTypedef pdt = new ParsedTypedef(ls.get(0), ls.get(1), ls.get(2));
            parsedTypedefs.add(pdt);
        }

        return parsedTypedefs;
    }

    public static void main(String[] args) {
        CANParser cp = new CANParser();

        List<ParsedTypedef> lpdf = cp.parseTypedefsDefault();
        List<ParsedMessage> lpm = cp.parseMessagesDefault();
        
        // Test the output using the names of parsedMessage
        for (ParsedMessage pm : lpm) { 
            System.out.println(pm.getName());
        }
    }
}
