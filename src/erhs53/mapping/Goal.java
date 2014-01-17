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
		HashMap<String, Double> G1 = new HashMap<>();
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
		COST.put("START", START);
	}
	

	public Goal(String name, Road road, int space) {
		this.name = name;
		this.road = road;
		this.space = space;		
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
