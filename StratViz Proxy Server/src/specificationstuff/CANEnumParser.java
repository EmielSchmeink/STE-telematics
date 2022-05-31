/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package specificationstuff;


//CONSULT DRIVE FOR EASIER OVERVIEW OF FUNCTIONS - https://drive.google.com/drive/u/0/folders/1vgE6a2_4SK2RJL6YsllVkD5Wmiu9INAC 


/*Note: Bytes should always be read from left to right. Enums are always represented using a single byte, whereas booleans are represented using a single bit. Integers can be 
* represented with upto 32 bits. Furthermore, the bytes that represent enums are typically equal to 00, 01, 02, 03 etc in hexadecimal. If a variable has three states it can only 
* be equal to 00, 01, and 02. Also, enums are allowed to have non-standard byte sequences. In this case STE defines a what the byte sequence of a specific state looks like in the
* CAN_typedef_2019_17-format.csv file.
*
* Examples: 
* signal A has 1 boolean that is set to true. The 8 bytes look as follows: 00000001 00000000 00000000 00000000 00000000 00000000 00000000 00000000
* signal B has 3 booleans in the following order (from top to bottom in CAN-X) true, false, true. 8 bytes: 00000101 00000000 00000000 00000000 00000000 00000000 00000000 00000000
* signal C has 2 booleans and 1 enum in the following order true true enumState2. 8 bytes: 00000011 00000010 00000000 00000000 00000000 00000000 00000000 00000000
* signal D has 2 booleans and 2 enum in the following order true enumState0 enumState1 true. 8 bytes: 00000001 00000000 00000001 00000001 00000000 00000000 00000000 00000000
* signal E has 3 booleans and a predefined enum state as 0x03 in the following order false false true enumState: 00000100 00000011 00000000 00000000 00000000 00000000 00000000 00000000
/*

/**
 *
 * @author 20172420
 */
import java.util.HashMap;
import java.util.List;
import java.util.HashMap;

public class CANEnumParser {
	//The idea of this class is that we receive a CAN message such as the string 'testmsg' down below, and we convert it to a hashmap that contains the name of the variable
	//together with its value. The example below should eventually output a hashmap that contains {(mode, ACUmode), (bmsAlive, T/F), (ssbAlive, T/F), (srvRegenAlive, T/F),
	// (inverter, InverterType)}. This hashmap can then be easily interpreted on the front-end.

	//Note that the current version of the file contains hardcoded examples to translate. Eventually we need to be able to translate the CAN message using only the CAN message itself.

    //The actual message we'll eventually need to parse
    static String testmsg = "(1600453413.322000) canx 12d#01c90100a819d400";
        
    //Data part of 'testmsg' (01c90100a819d400) converted to binary: 0001 11001001 00000001 00000000 10101000 00011001 11010100 00000000
        
     //These are strings that we will eventually need to deduce using the .csv files and the id of string 'testmsg'. The data types correspond to the following variables: 
	//mode, bmsAlive, ssbAlive, srvRegenAlive, esbAlive, inverter
     String msgdatatypes = "ACUMode, bool: 1, bool: 1, bool: 1, bool: 1, InverterType";
        
    //string msgdatatypes has ACUMode, booleans, and an InverterType. We will eventually need to deduce what these enums are 
    //by using the file 'CAN_typedef_2019_17-format.csv' file.
     static String ACUdef = "enum ACUMode:uint8_t{ACUModeRDW,ACUModeWSC,DARE}";
     static String InvertDef = "enum InverterType:uint8_t{InverterTypeUnknown,Tritium,NLE}";
        
        
	//The hashmap that includes the transformed data.
	static HashMap<String, CANTypedef> mapping = new HashMap<>();

	//This method creates a hashmap of bytes, and each of the possible values that this byte can denote. E.g. a byte 00000000 could either represent a boolean 'true', or it 
	//could alternatively, for another variable, denote a state 'Drive'. The hashmap will then contain {(00000000, true), (00000000, 'Drive')}. This method is used to parse
	//the .CAN_Typedef_2019_17-format.csv files.
	static void parseTypedef() {
		String[] result = ACUdef.split(",");

		CANTypedef type = new CANTypedef();

		type.name = result[0];
		type.description = result[2];

		type.options = new HashMap<Byte, String>();

		String optionstring = result[2];

		String options = optionstring.split("{")[1].split("}")[0];
		String optionsCSV = options.replace(" ", ""); // Replace spaces with no space if any exist

		String[] optionswhatever = optionsCSV.split(","); // [RDW=0x0] [WSC0x4] [DARE=0x5]

		for (byte i = 0; i < optionswhatever.length; i++) {
			if (optionswhatever[i].contains("=")) { // If the byte value is defined, use that one
				// Split on =
				String[] splits = optionswhatever[i].split("="); // [RWD] [0x0]
				byte bytee = Byte.parseByte(splits[1].split("x")[1]); // Read 0x0 as a byte
				type.options.put(bytee, splits[0]);
			} else {
				type.options.put(i, optionswhatever[i]);
			}
		}

		mapping.put(type.name, type);
	}

	//Takes a CAN message "(1600453413.322000) canx 12d#01c90100a819d400" (which is in hexadecimal notation and contains 8 bytes) to a list of bytes 
	//should be returned as a list that contains the following bytes: "00000001 11001001 00000001 00000000 10101000 00011001 11010100 00000000"
	public <Byte> List<Byte> parseData(String CANMessage) {
		List<Byte> list;
		byte temp = 1;
		return list;
	}


	//takes a CAN message "(1600453413.322000) canx 12d#01c90100a819d400" and returns the string id. The id is represented by the characters after canx and before the #. In this
	//example the id is denoted by 12d. Note: this is in hexadecimal. The id in this case is equal to 301.
	public String parseID(String CANMessage) {
		String temp = "id";
		return temp;
	}

	//Takes a CAN message "(1600453413.322000) canx 12d#01c90100a819d400" and returns the timestamp associated with it.
	public String parseTimestamp(String CANMessage) {
		String temp = "timestamp";
		return temp;
	}

	/*
	 Using the ID of the message in combination with the CAN overview, we can figure out what data types are present in the current CAN message.
	 E.g. in this case something along the lines of:
	 (int timestamp, ACUMode mode, bool bmsAlive, bool ssbAlive, bool srvRegenAlive, bool esbAlive, InverterType inverter)
	 
	 Note that ideally we'd like to have a list that contains multiple different types in a specific order. 
	 It therefore probably shouldn't return a List of Strings, but see it as a placeholder.
	 */
	public List<String> parseOverview(int id, int timestamp, String CANOverview) {
		List<String> temp = List.of("temp");
		return temp;
	}

	
	/*
	We can determine which bits belong to which data as this has a structured order. 
	We will create a hashmap that links these as follows:
	{(ACUMode mode, 00000001), (bool bmsAlive, 1), (bool ssbAlive, 0), 
	(bool srvRegenAlive, 1), (bool esbAlive, 1), (InverterType inverter, 00000110)}

	Note that we will probably save the timestamp and pass it along as a separate variable,
	 only to be added to the final result at the end. It would otherwise clutter the hashmap.
	*/
	public HashMap<String, String> determineBits(String resultOfParseOverviewFunction, String resultofparseDataFunction) {
		HashMap<String, String> temp = new HashMap<String, String>();
		return temp;
	}

	/* Finally, we use the hashmap created by determineBits() to determine what state the bytesequence
	on the left partains. The integers and booleans all have set bit/byte sequences (see top of this java file), 
	so those can be translated directly. The result should look as follows: 

	{
   "timestamp": "20:33:20"
   "name": "ACU_KeepAlive",
   "fields": [
	"ACU_Mode mode": "ACUModeRDW",
  	"bool bmsAlive": true,
	"bool ssbAlive": true,
	"bool srvRegenAlive": true,
	"bool esbAlive": true,
	"InverterType inverter": "Tritium"]
	}

	This is a specific format that allows for easier sending, receiving and parsing.
	*/
	public String determineConcreteData() {
		String temp = "temp";
		return temp;
	}

}

