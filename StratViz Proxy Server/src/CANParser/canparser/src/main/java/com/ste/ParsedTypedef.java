package com.ste;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class stores a single typedef that was parsed from a 
 * "CAN_typedef_2019_17-format.csv" file 
 * 
 * A single typedef line consists of three values: 
 * 
 * 1. A name
 * 2. A code
 * 3. A description
 * 
 * These values are stored in this object, together with the individiual states each typedef can take. 
 */
public class ParsedTypedef {

    // These are the header values of the typedefs.csv file
    private String name;
    private String code;
    private String description;

    // We also do some extra parsing to extract more information from the code variable
    private String codeType;
    private String[] codeStates; 

    // Use the constructor to set up a basic instance of a Parsedtypedef object
    public ParsedTypedef(String name, String code, String description) {
        // A ParsedTypedef object should have the basics
        this.name = name;
        this.code = code;
        this.description = description;
        
        // Do some additional parsing for the code variable
        parseCode();
    }
    
    public void prettyPrint() {
        System.out.println("-------------------------------------------------------");
        System.out.println("| Pretty printing all values for " + this.name.toUpperCase());
        System.out.println("| +++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("| Type: " + this.codeType);
        System.out.print("| States: " );
        for (String cs : codeStates) {
            System.out.print(cs + " ");
        }
        System.out.println();
        System.out.println("| Description: " + this.description);
        System.out.println("| +++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("-------------------------------------------------------");
    }

    private void parseCode() {
        // Check for the type 
        if (this.code.contains("uint8_t")) {
            this.codeType = "uint8_t";
            
        // TODO: find out if different types exist for the CAN spec
        } else if (this.code.contains("??")) { 

        }

        // Extract the states from the code using a regular expression, which have the format "{state1, state2, state3, ...}"
        String pattern = "[{](.*)[}]";
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(this.code);
        
        if (m.find())
            this.codeStates = m.group(1).split(",");
        
            //TODO: exception handling when format isn't correct
    }   

    // Some basic getters to retrieve instance variables
    public String[] getCodeStates() {
        return this.codeStates;
    }
    
    public String getCodeType() {
        return this.codeType;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    //TODO: maybe also implement setters for name, code and description 
}
