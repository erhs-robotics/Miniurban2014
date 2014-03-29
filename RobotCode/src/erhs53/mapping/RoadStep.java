package erhs53.mapping;

public class RoadStep extends Step {
	
	public static enum Direction { none, left, right, straight }
	public final boolean slow;
	public final boolean circle;	
	
	
	public RoadStep(String name, Direction direction, boolean slow, boolean circle) {
		this.name = name;
		this.slow = slow;
		this.circle = circle;
		this.direction = direction;
		
	}

}
