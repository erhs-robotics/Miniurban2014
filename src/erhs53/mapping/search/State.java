package erhs53.mapping.search;

import java.util.ArrayList;

/**
 * Used to define the needed fields and methods for a State
 * @author Michael Stevens
 *
 */
public abstract class State {
	public String name;
	public double cost;
	public abstract ArrayList<Action> actions(Path path);	
}
