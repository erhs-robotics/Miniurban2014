package erhs53.mapping;

import java.util.ArrayList;

import erhs53.mapping.search.Action;
import erhs53.mapping.search.State;



public class Road extends State {	
	public double length;
	public boolean slow, circle;
	public ArrayList<Action> actions;
	
	public Road(String name, double length) {
		this.actions = new ArrayList<>();
		this.name = name;
		this.length = length;		
		this.slow = false;
		this.circle = false;
		this.cost = slow ? length * 2.0 : length;//CHANGE ME!!!!!! 2 is the wrong constant!!!!!!!!!
	}
	
	public Road(String name, double length, boolean slow, boolean circle) {
		this.actions = new ArrayList<>();
		this.name = name;
		this.length = length;
		this.slow = slow;
		this.circle = circle;
		if(circle) length *= 2;//CHANGE ME!!!!!!!! 2 IS THE WRONG CONSTANT
	}	
	
	public void setActions(Action... theActions) {
		this.actions = new ArrayList<Action>();
		for(Action a : theActions) {			
			this.actions.add(a);
		}		
	}

	@Override
	public ArrayList<Action> actions() {
		return actions;
	}

}