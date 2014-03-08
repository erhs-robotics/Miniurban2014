package erhs53.mapping;

public class RoadStep extends Step {	
	public boolean slow;
	public boolean circle;
	
	public RoadStep(String name, boolean slow, boolean circle) {
		this.name = name;
		this.slow = slow;
		this.circle = circle;
	}

}
