package CANParser.canparser.src.main.java.com.ste;

import java.util.ArrayList;

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
    private String[] fieldNames; 
    private String[] dataTypes;
    private String[] units;
    private String[] defaultValues;
    private String description;
    private String[] senders;
    private String[] receivers;
    private String sendInterval;
    private String convertEndianess;
    private String type;
    private String properties;
    private String sendFrequency;

    // A bit messy, but set up a ParsedMessage object and define its instance variables
    public ParsedMessage(String id, String name, String fieldNames, String dataTypes, String units, String defaultValues,
                        String description, String senders, String receivers, String sendInterval, String convertEndianess, 
                        String type, String properties, String sendFrequency) {
        this.id = id;
        this.name = name; 
        this.fieldNames = fieldNames.split(",");
        this.dataTypes = dataTypes.split(",");
        this.units = units.split(",");
        this.defaultValues = defaultValues.split(",");
        this.description = description; 
        this.senders = senders.split(",");
        this.receivers = receivers.split(",");
        this.sendInterval = sendInterval;
        this.convertEndianess = convertEndianess;
        this.type = type;
        this.properties = properties;
        this.sendFrequency = sendFrequency;
    }


    public void prettyPrint() {
        System.out.println("-------------------------------------------------------");
        System.out.println("| Pretty printing all values for " + this.name.toUpperCase());
        System.out.println("| +++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("| ID: " + this.id);
        System.out.println("| Name: " + this.name);
        System.out.println("| Field Names: " + this.fieldNames);
        System.out.println("| Data Types: " + this.dataTypes);
        System.out.println("| Units: " + this.units);
        System.out.println("| Default Values: " + this.defaultValues);
        System.out.println("| Description: " + this.description);
        System.out.println("| Senders: " + this.senders);
        System.out.println("| Receivers: " + this.receivers);
        System.out.println("| Send Interval: " + this.sendInterval);
        System.out.println("| Convert Endianness: " + this.convertEndianess);
        System.out.println("| Type: " + this.type);
        System.out.println("| Properties: " + this.properties);
        System.out.println("| Send Frequency: " + this.sendFrequency);
        System.out.println("| +++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("-------------------------------------------------------");
    }

    // Basic getters for all instance variables are found below
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String[] getFieldNames() {
        return this.fieldNames;
    }

    public String[] getDataTypes() {
        return this.dataTypes;
    }

    public String[] getUnits() {
        return this.units;
    }

    public String[] getDefaultValues() {
        return this.defaultValues;
    }

    public String getDescription() {
        return this.description;
    }

    public String[] getSenders() {
        return this.senders;
    }

    public String[] getReceivers() {
        return this.receivers;
    }

    public String getSendInterval() {
        return this.sendInterval;
    }

    public String getConvertEndianness() {
        return this.convertEndianess;
    }

    public String getType() {
        return this.type;
    }

    public String getProperties() {
        return this.properties;
    }

    public String getSendFrequency() {
        return this.sendFrequency;
    }

}
