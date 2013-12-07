package erhs53.mapping;

import java.awt.Point;
import java.util.ArrayList;

public class Road {
	public String name;
	public double length;
	public boolean slow, circle;
	public ArrayList<Action> actions;
	
	public Point startPos, endPos;
	
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
		if(circle) length *= 2;//CHANGE ME!!!!!!!! 2 IS THE WRONG CONSTANT
	}
	public Road(String name, double length, boolean slow, boolean circle, Point startPos, Point endPos) {
		this(name, length, slow, circle);
		this.startPos = startPos;
		this.endPos = endPos;
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