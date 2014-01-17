package erhs53.mapping;

import java.util.ArrayList;
import java.util.HashMap;

import erhs53.mapping.search.Action;
import erhs53.mapping.search.Path;
import erhs53.mapping.search.State;

public class Goal extends State {
	public int space, dir;
	public Road road;
	public static HashMap<Goal, HashMap<Goal, Double>> COST;
	public ArrayList<Action> actions;
	
	static {
		
		HashMap<Goal, Double> G1 = new HashMap<>();
		G1.put(Map.G2, 6.0);
		G1.put(Map.G3, 10.0);
		G1.put(Map.START, 15.0);
		
		HashMap<Goal, Double> G2 = new HashMap<>();
		G2.put(Map.G1, 10.0);
		G2.put(Map.G3, 4.0);
		G2.put(Map.START, 9.0);
		
		HashMap<Goal, Double> G3 = new HashMap<>();
		G3.put(Map.G1, 6.0);
		G3.put(Map.G2, 8.0);
		G3.put(Map.START, 5.0);
		
		HashMap<Goal, Double> START = new HashMap<>();
		START.put(Map.G1, 10.0);
		START.put(Map.G2, 4.0);
		START.put(Map.G3, 13.0);
		
		COST = new HashMap<>();
		COST.put(Map.G1, G1);
		COST.put(Map.G2, G2);
		COST.put(Map.G3, G3);
		COST.put(Map.START, START);
	}
	

	public Goal(String name, Road road, int space) {
		this.name = name;
		this.road = road;
		this.space = space;		
	}
	
	public double cost(Goal g) {
		return COST.get(this).get(g);
	}
	
	public void set(int space, int dir) {
		this.space = space;
		this.dir = dir;
	}
	
	public ArrayList<Action> actions(Path path) {
		if(path.length() >= 4) {
			@SuppressWarnings("unchecked")
			ArrayList<Action> newActions = (ArrayList<Action>) actions.clone();
			newActions.add(new Action(0, Map.END));
			return newActions;
		}
			
		return actions;
	}

}
