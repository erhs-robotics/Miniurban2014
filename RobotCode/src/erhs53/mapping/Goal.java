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
	
	public Goal(String name, Road road) {
		this.name = name;
		this.road = road;				
	}
	
	/**
	 * The cost of traveling from this goal to goal g
	 * @param g
	 * @return
	 */
	public double cost(Action a) {	
		return COST.get(name).get(a.state.name);
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
			ArrayList<Action> newActions = Action.clone(actions);
			newActions.add(new Action(0, Map.END));
			return newActions;
		}
			
		return actions;
	}

}
