package specificationstuff;

import java.util.HashMap;

public class CANEnumParser {
	static String teststring = "ACUMode,\"enum class ACUMode : uint8_t {RDW,WSC,DARE}\",ACU mode";

	static HashMap<String, CANTypedef> mapping = new HashMap<>();

	static void parse() {
		String[] result = teststring.split(",");

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
}
