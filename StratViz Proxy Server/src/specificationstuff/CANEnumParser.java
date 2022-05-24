/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package specificationstuff;


/*Notes to self: Bytes should always be read from left to right. Enums are always represented using a single byte, whereas booleans are represented using a single bit. Integers can be 
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
	//the .csv files.
	static void parse() {
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

	//Will be used to translate the data (which is in hexadecimal notation and contains 8 bytes) to a list of bytes.
	public <Byte> List<Byte> convertHexadecimal(int hex) {
		List<Byte> list;
		return list;
	}

	//Determines the options available for a specific enum variable (e.g. for variable vehicleState the options are 
	//{VehicleStateStandby,Demo,Auto,Drive,VehicleStateWSC,VehicleStateESS,ExternalVehicleStateCharging}), and stores these options in type.options
	public void parseOptions() {

	}

}

