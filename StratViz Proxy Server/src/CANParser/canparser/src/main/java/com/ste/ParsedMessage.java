package com.ste;

/**
 * This class stores a single message that was parsed from 
 * a "CAN_overview_2019_17-format.csv" file
 * 
 * A single message consists of 16 different fields, which are the following: 
 * id - name - DataNames - DataTypes - DataUnits - Standard Values - Log - PutToCan1 - PutToCan2 - 
 * Description - Sender - Receiver - ToStrat - ToVis - NotToDB - Convert Endiannes
 * 
 * These individual fields can be requested by calling the corresponding getter method. 
 * 
 * No additional processing is done on these fields. 
 */

//TODO: Convert this class to make use of the Builder design pattern
public class ParsedMessage {

    // Declare all fields as String variables
    private String id; 
    private String name; 
    private String dataNames; 
    private String dataTypes;
    private String dataUnits;
    private String standardValues;
    private String log;
    private String putToCan1;
    private String putToCan2;
    private String description;
    private String sender;
    private String receiver;
    private String toStrat;
    private String toVis;
    private String notToDB;
    private String convertEndianness;

    // A bit messy, but set up a ParsedMessage object and define its instance variables
    public ParsedMessage(String id, String name, String dataNames, String dataTypes, String dataUnits,
    String standardValues, String log, String putToCan1, String putToCan2, String description,
    String sender, String receiver, String toStrat, String toVis, String notToDB, String converEndianness) {
        this.id = id;
        this.name = name;
        this.dataNames = dataNames;
        this.dataTypes = dataTypes;
        this.dataUnits = dataUnits;
        this.standardValues = standardValues;
        this.log = log; 
        this.putToCan1 = putToCan1;
        this.putToCan2 = putToCan2;
        this.description = description;
        this.sender = sender;
        this.receiver = receiver; 
        this.toStrat = toStrat;
        this.toVis = toVis;
        this.notToDB = notToDB;
        this.convertEndianness = converEndianness;
    }

    // 
    public void prettyPrint() {
        System.out.println("-------------------------------------------------------");
        System.out.println("| Pretty printing all values for " + this.name.toUpperCase());
        System.out.println("| +++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("| ID: " + this.id);
        System.out.println("| Name: " + this.name);
        System.out.println("| Data Names: " + this.dataNames);
        System.out.println("| Data Types: " + this.dataTypes);
        System.out.println("| Data Units: " + this.dataUnits);
        System.out.println("| Standard Values: " + this.standardValues);
        System.out.println("| Log: " + this.log);
        System.out.println("| PutToCan1: " + this.putToCan1);
        System.out.println("| PutToCan2: " + this.putToCan2);
        System.out.println("| Description: " + this.description);
        System.out.println("| Sender: " + this.sender);
        System.out.println("| Receiver: " + this.receiver);
        System.out.println("| ToStrat: " + this.toStrat);
        System.out.println("| ToVis: " + this.toVis);
        System.out.println("| NotToDB: " + this.notToDB);
        System.out.println("| Convert Endianness: " + this.convertEndianness);
        System.out.println("| +++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("-------------------------------------------------------");
    }

    // Basic getters for all instance variables are found below
    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDataNames() {
        return this.dataNames;
    }

    public String getDataTypes() {
        return this.getDataTypes();
    }

    public String getDataUnits() {
        return this.getDataUnits();
    }

    public String getStandardValues() {
        return this.getStandardValues();
    }

    public String getLog() {
        return this.log;
    }

    public String getPutToCan1() {
        return this.putToCan1;
    }

    public String getPutToCan2() {
        return this.putToCan2;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSender() {
        return this.sender;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public String getToStrat() {
        return this.toStrat;
    }

    public String getToVis() {
        return this.toVis;
    }

    public String getNotToDB() {
        return this.notToDB;
    }

    public String getConvertEndianess() {
        return this.convertEndianness;
    }

}
