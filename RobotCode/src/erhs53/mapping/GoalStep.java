package erhs53.mapping;

import erhs53.mapping.RoadStep.Direction;

public class GoalStep extends Step {	
	public int space;
	
	public GoalStep(String name, int space, Direction direction) {
		this.name = name;
		this.space = space;
		this.direction = direction;		
	}

}
