package erhs53.mapping.search;

public abstract class Action {
	public int type;
	public State state;
	
	public Action(int type, State state) {
		this.type = type;
		this.state = state;
	}
	
}
