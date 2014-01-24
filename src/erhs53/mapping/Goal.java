package erhs53.mapping;

import java.util.ArrayList;
import java.util.HashMap;

import erhs53.mapping.search.Action;
import erhs53.mapping.search.Path;
import erhs53.mapping.search.State;

public class Goal extends State {
	public int space; /** The number of the space to park in the parking lot */
	public int dir;/** The direction in which to park (left or right) */
	public Road road;/** The Road that the Parking Lot is on */
	/** A map of the cost from each goal, to every other goal*/
	public static HashMap<String, HashMap<String, Double>> COST;
	public ArrayList<Action> actions;/** The possible goals to travel to next */
	
	// Build the COST map
	static {
		// For each goal, store the cost of traveling to each other goal
		/*HashMap<String, Double> G1 = new HashMap<>();
		G1.put("G2", 4.0);
		G1.put("G3", 6.0);
		G1.put("END", 9.0);

		HashMap<String, Double> G2 = new HashMap<>();
		G2.put("G1", 6.0);
		G2.put("G3", 2.0);
		G2.put("END", 5.0);

		HashMap<String, Double> G3 = new HashMap<>();
		G3.put("G1", 4.0);
		G3.put("G2", 4.0);
		G3.put("END", 3.0);

		HashMap<String, Double> START = new HashMap<>();
		START.put("G1", 2.0);
		START.put("G2", 6.0);
		START.put("G3", 8.0);

		COST = new HashMap<>();
		COST.put("G1", G1);
		COST.put("G2", G2);
		COST.put("G3", G3);
		COST.put("START", START);*/
		
		HashMap<String, Double> G1 = new HashMap<>();
		G1.put("G2", 1030.0);
		G1.put("G3", 535.0);
		G1.put("G4", 90.0);
		G1.put("G5", 455.0);
		G1.put("G6", 615.0);
		G1.put("G7", 690.0);
		G1.put("G8", 640.0);
		G1.put("G9", 1105.0);
		G1.put("END", 835.0);

		HashMap<String, Double> G2 = new HashMap<>();
		G2.put("G1", 415.0);
		G2.put("G3", 535.0);
		G2.put("G4", 90.0);
		G2.put("G5", 455.0);
		G2.put("G6", 615.0);
		G2.put("G7", 690.0);
		G2.put("G8", 640.0);
		G2.put("G9", 1105.0);
		G2.put("END", 835.0);

		HashMap<String, Double> G3 = new HashMap<>();
		G3.put("G1", 580.0);
		G3.put("G2", 1195.0);
		G3.put("G4", 670.0);
		G3.put("G5", 505.0);
		G3.put("G6", 455.0);
		G3.put("G7", 740.0);
		G3.put("G8", 690.0);
		G3.put("G9", 960.0);
		G3.put("END", 1000.0);

		HashMap<String, Double> G4 = new HashMap<>();
		G4.put("G1", 650.0);
		G4.put("G2", 1265.0);
		G4.put("G3", 445.0);
		G4.put("G5", 575.0);
		G4.put("G6", 525.0);
		G4.put("G7", 810.0);
		G4.put("G8", 760.0);
		G4.put("G9", 1030.0);
		G4.put("END", 1070.0);

		HashMap<String, Double> G5 = new HashMap<>();
		G5.put("G1", 740.0);
		G5.put("G2", 1355.0);
		G5.put("G3", 895.0);
		G5.put("G4", 830.0);
		G5.put("G6", 165.0);
		G5.put("G7", 1005.0);
		G5.put("G8", 955.0);
		G5.put("G9", 670.0);
		G5.put("END", 1160.0);

		HashMap<String, Double> G6 = new HashMap<>();
		G6.put("G1", 905.0);
		G6.put("G2", 1520.0);
		G6.put("G3", 1060.0);
		G6.put("G4", 995.0);
		G6.put("G5", 925.0);
		G6.put("G7", 840.0);
		G6.put("G8", 790.0);
		G6.put("G9", 505.0);
		G6.put("END", 1325.0);

		HashMap<String, Double> G7 = new HashMap<>();
		G7.put("G1", 1105.0);
		G7.put("G2", 1720.0);
		G7.put("G3", 1640.0);
		G7.put("G4", 1195.0);
		G7.put("G5", 835.0);
		G7.put("G6", 1000.0);
		G7.put("G8", 700.0);
		G7.put("G9", 415.0);
		G7.put("END", 1525.0);

		HashMap<String, Double> G8 = new HashMap<>();
		G8.put("G1", 825.0);
		G8.put("G2", 1440.0);
		G8.put("G3", 1360.0);
		G8.put("G4", 915.0);
		G8.put("G5", 555.0);
		G8.put("G6", 720.0);
		G8.put("G7", 470.0);
		G8.put("G9", 885.0);
		G8.put("END", 1245.0);

		HashMap<String, Double> G9 = new HashMap<>();
		G9.put("G1", 775.0);
		G9.put("G2", 1390.0);
		G9.put("G3", 1310.0);
		G9.put("G4", 865.0);
		G9.put("G5", 505.0);
		G9.put("G6", 670.0);
		G9.put("G7", 420.0);
		G9.put("G8", 370.0);
		G9.put("END", 1195.0);

		HashMap<String, Double> START = new HashMap<>();
		START.put("G1", 820.0);
		START.put("G2", 405.0);
		START.put("G3", 445.0);
		START.put("G4", 495.0);
		START.put("G5", 860.0);
		START.put("G6", 900.0);
		START.put("G7", 1095.0);
		START.put("G8", 1045.0);
		START.put("G9", 1405.0);

		COST = new HashMap<>();
		COST.put("G1", G1);
		COST.put("G2", G2);
		COST.put("G3", G3);
		COST.put("G4", G4);
		COST.put("G5", G5);
		COST.put("G6", G6);
		COST.put("G7", G7);
		COST.put("G8", G8);
		COST.put("G9", G9);
		COST.put("START", START);

	}
	

	public Goal(String name, Road road) {
		this.name = name;
		this.road = road;
				
	}
	
	/**
	 * The cost of traveling from this goal to goal g
	 * @param g
	 * @return
	 */
	public double cost(State g) {		
		return COST.get(name).get(g.name);
	}
	
	/**
	 * A convenience function used to set the parking space number and direction
	 * @param space
	 * @param dir
	 */
	public void set(int space, int dir) {
		this.space = space;
		this.dir = dir;
	}
	
	/**
	 * Returns the possible goals to travel to next. If all goals have been visited, then
	 * Map.END is appended to the list of options
	 */
	public ArrayList<Action> actions(Path path) {
		//if all goals have been visited, append Map.END to the list of options
		if(path.length() >= Map.goalNum + 1) {
			@SuppressWarnings("unchecked")
			ArrayList<Action> newActions = (ArrayList<Action>) actions.clone();
			newActions.add(new Action(0, Map.END));
			return newActions;
		}
			
		return actions;
	}

}
