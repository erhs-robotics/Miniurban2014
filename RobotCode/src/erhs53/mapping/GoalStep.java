package erhs53.mapping;

public class GoalStep extends Step {	
	private static final long serialVersionUID = -1294398647504677036L;
	public boolean left = false;
	public int space;
	
	public GoalStep(String name, int space, boolean left) {
		this.name = name;
		this.space = space;
		this.left = left;
	}

}
