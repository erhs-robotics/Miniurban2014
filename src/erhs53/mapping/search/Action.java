package erhs53.mapping.search;
/**
 * Used to conveniently store type, state tuples
 * 
 * @author Michael Stevens
 *
 */
public class Action {	
	public int type; /**Represents what actions is needed to reach the state (ex: turn left) */
	public State state; /**The state that is reached by the action defined by type */
	
	public Action(int type, State state) {
		this.type = type;
		this.state = state;
	}	
}
