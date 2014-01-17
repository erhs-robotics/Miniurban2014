package erhs53.mapping.search;

import java.util.ArrayList;

public abstract class State {
	public String name;
	public double cost;
	public abstract ArrayList<Action> actions(Path path);	
}
