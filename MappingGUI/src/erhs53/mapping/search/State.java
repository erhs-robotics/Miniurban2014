package erhs53.mapping.search;

import java.util.ArrayList;

/**
 * Used to define the needed fields and methods for a State
 * @author Michael Stevens
 *
 */
public abstract class State {
	/**
	 * A unique identifier for the state
	 */
	public String name;
	
	/**
	 * The cost of moving from State this to State state
	 * @param state
	 * @return
	 */
	public abstract double cost(Action action);
	
	/**
	 * The available actions in the State this. For Goals,
	 * the actions available is dependent on the path.
	 * @param path
	 * @return
	 */
	public abstract ArrayList<Action> actions(Path path);	
}
