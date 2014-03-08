package erhs53.mapping;

public class GoalStep extends Step {	
	public boolean left = false;
	public int space;
	
	public GoalStep(String name, int space, boolean left) {
		this.name = name;
		this.space = space;
		this.left = left;
	}

}
