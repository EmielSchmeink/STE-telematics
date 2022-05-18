package specificationstuff;

import java.util.ArrayList;

public class CANMessage {

	public int id; // Should technically be unsigned, but it is assumed that CANMessage id's don't
					// exceed INT_MAX
	public String Name;
	// Map that
	public ArrayList<String> fieldNames = new ArrayList<String>();
	public ArrayList<String> fields = new ArrayList<String>();

	// What types can our data be: bool, integer

}
