package specificationstuff;

import java.util.ArrayList;
import java.util.HashMap;

public class Spectest {

	String test = "0,CANOpen_NMT,\"command, node\",\"CANOpenNMTCommand, uint8_t\",\"command, node ID\",\"CANOpenNMTCommand::Null, 0\",CANopen NMT command,ACU,\"SAC, BAC\",,1,A,,0";

	static HashMap<Byte, CANMessage> rules = new HashMap<Byte, CANMessage>();

	public static void main(String[] args) {

		CANEnumParser.parse(); // This reads the entire Typedefs.csv and creates Enum like definitions

		// CANMessage definitions
		// Split test on ,
		CANMessage msg = new CANMessage();

		msg.id = 0;
		msg.Name = "CANOpen_NMT";
		msg.fieldNames = new ArrayList<String>();
		// Strip fields based on "" and ,
		msg.fieldNames.add("command");
		msg.fieldNames.add("node");

		msg.fields = new ArrayList<String>();
		msg.fields.add("CANOpenNMTCommand");
		msg.fields.add("uint8_t");

		rules.put((byte) msg.id, msg);

		////////////
		String readstring = "(1600453413.400000) canx 2ee#6314d576e8e47776";

		// Split the string into its sections
		// {timestamp: "1002420"

		byte msgId = Byte.parseByte("2ee");

		// Create json object or something
		CANMessage rule = rules.get(msgId);

		for (String field : rule.fields) {
			switch (field) {
				case "uint8_t":
					break;
				case "float":
					break;
				default:
					CANTypedef CANtype = CANEnumParser.mapping.get(field);
					if (CANtype != null) {
						// Read the first byte
						byte code = Byte.parseByte("63");
						String state = CANtype.options.get(code);
						// "DRIVE"
					} else {
						String state = "UNKNOWN TYPE";
					}
			}
		}

		// {timestamp: 160000, "id": 0, "name": "CANOpenNMT", "command": "DRIVE",
		// "node": 8, ...}
	}
}
