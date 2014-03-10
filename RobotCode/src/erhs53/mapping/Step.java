package erhs53.mapping;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import erhs53.utilities.StringUtils;

public abstract class Step{
	public String name;
	
	public static final Step[] loadSteps(String file) throws IOException {
		LinkedList<Step> steps = new LinkedList<>();

		InputStream is = new FileInputStream(new File(file));
		DataInputStream din = new DataInputStream(is);
		StringBuffer strBuffer = new StringBuffer();		

		while (is.available() > 3) { // at least 4 bytes left to read
			// Read a line from the file
			char ch;
			do {
				ch = (char) din.readByte();
				strBuffer.append(ch);			
			} while(ch != '\n');
			String string = strBuffer.toString();
			
			strBuffer.setLength(0);
			
			// Split the string
			String parts[] = StringUtils.splitString(string, ",");
			
			// Translate the String into a Step object
			if(parts[0].equals("road")) {
				String name = parts[1];
				boolean slow = Boolean.parseBoolean(parts[2]);
				boolean circle = Boolean.parseBoolean(parts[3]);
				steps.add(new RoadStep(name, slow, circle));
			} else if(parts[0].equals("goal")) {
				String name = parts[1];
				int space = Integer.parseInt(parts[2]);
				boolean left = Boolean.parseBoolean(parts[3]);
				steps.add(new GoalStep(name, space, left));
			} else {
				System.out.println("Error reading map!");
			}			
		}
		din.close();

		return steps.toArray(new Step[steps.size()]);
	}
}
