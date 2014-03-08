package erhs53;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import sun.security.util.Length;
import erhs53.mapping.Goal;
import erhs53.mapping.GoalStep;
import erhs53.mapping.Map;
import erhs53.mapping.Road;
import erhs53.mapping.RoadStep;
import erhs53.mapping.Step;
import erhs53.mapping.search.Action;
import erhs53.mapping.search.Path;
import erhs53.mapping.search.State;

public class Mapper {

	static void printCodeArray(Path p) {
		System.out.printf("Action[] actions = new Action[%s];\n",
				p.actions.size());
		for (int i = 0; i < p.actions.size(); i++) {
			Action a = p.actions.get(i);
			System.out.printf("actions[%s] = new Action(%s, %s);\n", i, a.type,
					"Map." + a.state.name);
		}
	}

	public static void writePath(Path path) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter("steps.info");
		for (Action a : path.actions) {
			State state = a.state;
			if (state instanceof Road) {
				Road road = (Road) state;
				fileOut.print("road,");
				fileOut.print(road.name + ",");
				fileOut.print(road.slow + ",");
				fileOut.println(road.circle);
			} else if (state instanceof Goal) {
				Goal goal = (Goal) state;
				fileOut.print("goal,");
				fileOut.print(goal.name + ",");
				fileOut.print(goal.space + ",");
				fileOut.println(goal.dir == Map.PL);
			}
		}
		fileOut.close();

	}
	
	private static String[] splitString(String str, String key) {		
		LinkedList<String> parts = new LinkedList<>();
		int start = 0;
		int end = str.indexOf(key);
		
		while(end != -1) {
			if(start != end)
				parts.add(str.substring(start, end));
			
			start = end + key.length();
			end = str.indexOf(key, start);
			//System.out.println(end);
		}
		if(start != str.length())
		parts.add(str.substring(start, str.length()));
		
		return parts.toArray(new String[parts.size()]);
	}

	public static Step[] readPath() throws IOException {
		LinkedList<Step> steps = new LinkedList<>();

		InputStream is = new FileInputStream(new File("steps.info"));
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
			String parts[] = splitString(string, ",");
			
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

	public static void main(String[] args) throws IOException {
		Map.G1.set(1, Map.PR);
		Map.G2.set(1, Map.PL);
		Map.G9.set(1, Map.PR);

		Path p = Map.generatePath(Map.G1, Map.G2, Map.G9);
		if (p == null) {
			System.out.println("Failed");
			return;
		}

		//writePath(p);
		Step[] steps = readPath();
		for(Step step : steps) {
			
			System.out.println(step.name);
			
			
		}
		

	}
}
