package com.telematics;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.*;

/**
 * The supplied default typedef file consists of three fields: Name - Code - Description
 * 
 * These fields are seperated by commas (it's a CSV after all) but also by quotes. We have to be careful, 
 * as a Code field can contain commas within the field. 
 * Therefore we split by performing the following operations in this order: 
 * 
 * 1. Split by a comma, this get us 2 fields: the Name field and the Code + Description field
 * 2. Split by quotes, this subdivides the Code + Description field into a separate Code field and Description field
 * 
 * We can then further subdivide the Code field to allow us to read different values within a Code field
 */
public class CANParser {

    public List<ParsedMessage> parseMessagesDefault() {
        // Define our CSVReader variable which will be used to read a CAN_overview.csv file
        CSVReader reader = null; 

        // Define the ArrayList which will store the lines in the String format
        List<List<String>> arrayOfLines = null; 

        try { 
            // Instantiate a CSVReader object and use it to read the default messages file 
            reader = new CSVReaderBuilder(
                new FileReader("CANParser\\src\\res\\CAN_overview_2019_17-format.csv"))
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
                new FileReader("CANParser\\src\\res\\CAN_typedef_2019_17-format.csv"))
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

        for (ParsedMessage pm : lpm) {
            pm.prettyPrint();
        }

        for (ParsedTypedef ptd : lpdf) {
            ptd.prettyPrint();
        }
    }
}
