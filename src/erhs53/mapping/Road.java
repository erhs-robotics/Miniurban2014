package erhs53.mapping;

import java.util.ArrayList;

public class Road {
	public String name;
	public double length;
	public boolean slow, circle;
	public ArrayList<Action> actions;
	
	public Road(String name, double length) {
		this.actions = new ArrayList<>();
		this.name = name;
		this.length = length;		
		this.slow = false;
		this.circle = false;		
	}
	
	public Road(String name, double length, boolean slow, boolean circle) {
		this.actions = new ArrayList<>();
		this.name = name;
		this.length = length;
		this.slow = slow;
		this.circle = circle;		
	}
	
	public double cost() {
		return slow ? length * 2.0 : length;//CHANGE ME!!!!!! 2 is the wrong constant!!!!!!!!!
	}

	public void setActions(Action... theActions) {
		this.actions = new ArrayList<Action>();
		for(Action a : theActions) {			
			this.actions.add(a);
		}		
	}

}