package erhs53.mapping;

import java.util.ArrayList;

import erhs53.mapping.search.Action;
import erhs53.mapping.search.Path;
import erhs53.mapping.search.State;

/**
 * Used to represent every Road on the map. A Roads are defined from
 * stop sign to stop sign.
 * @author Michael Stevens
 *
 */


public class Road extends State {	
	public double length; /** The length of the road */
	public boolean slow; /** Is the road "slow"? (indicated by yellow markings) */
	public boolean circle; /** Is the road part of a circle */
	public double cost; /** The total cost of traversing the road */
	private ArrayList<Action> actions;/** The Roads you can turn on to from this one*/	
	
	public Road(String name, double length) {
		this.actions = new ArrayList<>();
		this.name = name;
		this.length = length;		
		this.slow = false;
		this.circle = false;
		this.cost = slow ? length * 2.0 : length;//CHANGE ME!!!!!! 2 is the wrong constant!!!!!!!!!
	}
	
	public Road(String name, double length, boolean slow, boolean circle) {
		this.actions = new ArrayList<>();
		this.name = name;
		this.length = length;
		this.slow = slow;
		this.circle = circle;
		// Make circles extra expensive because traveling them is risky
		if(circle) length *= 2;//CHANGE ME!!!!!!!! 2 IS THE WRONG CONSTANT
		this.cost = slow ? length * 2.0 : length;//CHANGE ME!!!!!! 2 is the wrong constant!!!!!!!!!
	}	
	
	/**
	 * Convenience function that sets the road successors
	 * @param theActions
	 */
	public void setActions(Action... theActions) {
		this.actions = new ArrayList<Action>();
		for(Action a : theActions) {			
			this.actions.add(a);
		}		
	}
	
	/**
	 * Returns the possible roads to turn on to. In this case
	 * actions are not dependent on the path
	 */
	public ArrayList<Action> actions(Path path) {
		return actions;
	}
	
	/**
	 * The cost of going from the start of this Road to the end of
	 * Road s
	 */
	public double cost(State s) {
		return ((Road) s).cost;
	}

}