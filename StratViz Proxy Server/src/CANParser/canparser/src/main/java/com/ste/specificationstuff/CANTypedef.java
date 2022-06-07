package com.ste.specificationstuff;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.ste.CANParser;
import com.ste.ParsedTypedef;

// TODO: should the Byte be the key or the String? ASK MATHIJS!
// @author Emre Aydogan
public class CANTypedef {

	// Read through the
	public String name; // Name of the Enum
	public HashMap<Byte, String> options; // A mapping from a Byte to one of the enum options
	public String description; // The description of the EnumType

	/**
	 * Create a Hashmap for each Enum which contains the byte sequence for that specific enum
	 * 
	 * @return
	 */
	public HashMap<String, HashMap<Byte, String>> parseTypedef() {

		HashMap<String, HashMap<Byte, String>> parsedEnums = new HashMap<>();

		// First we make use of the CANParser class to read in the default typedefs.csv file and extract all the fields per typedef
		CANParser cp = new CANParser(); 
		
		// Store all the parsed typedefs in a list of ParsedTypedef objects
		List<ParsedTypedef> lpdf = cp.parseTypedefsDefault();

		// Now iterate through the list of parsed typedefs and build the Hashmap by getting the name of the enum and its codestates.
		// Assign the corresponding Byte value to it and store it in the Hashmap

		for (ParsedTypedef pdf : lpdf) {

			// Build the Hashmap<Byte, String> first (e.g. the value of a parsed enum)
			HashMap<Byte, String> byteStateMap = new HashMap<>();

			for (byte i = 0; i < pdf.getCodeStates().length; i++) {	
				byteStateMap.put(i, pdf.getCodeStates()[i]);
				
			}
			
			// Now that we have mapped all the states to a Byte value, map this inner hashmap (value) to an enum name (key)
			parsedEnums.put(pdf.getName(), byteStateMap);
			System.out.println(parsedEnums);
			byteStateMap.clear();
		}

		// DEBUG TEST
		// for (String s : parsedEnums.keySet()) {
		// 	// System.out.print(s + " ");
		// 	HashMap<Byte, String> map = parsedEnums.get(s);
			
		// 	System.out.println(map);


		// 	System.out.println("help");
		// }

		return parsedEnums;
	}
}
