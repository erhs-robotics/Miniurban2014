package erhs53.mapping.search;

import java.util.ArrayList;

/**
 * Used to conveniently store type, state tuples
 * 
 * @author Michael Stevens
 *
 */
public class Action {
	
	// ======================================================
	// Variables
	// ======================================================
	
	public int type; /**Represents what actions is needed to reach the state (ex: turn left) */
	public State state; /**The state that is reached by the action defined by type */
	
	// ======================================================
	// Constructor
	// ======================================================
	
	public Action(int type, State state) {
		this.type = type;
		this.state = state;
	}
	
	// ======================================================
	// Class Logic
	// ======================================================
	
	public static ArrayList<Action> clone(ArrayList<Action> x) {
		ArrayList<Action> clone = new ArrayList<>();
		clone.addAll(x);
		return clone;		
	}
}
