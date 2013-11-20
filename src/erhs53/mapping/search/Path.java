package erhs53.mapping.search;

import java.util.ArrayList;

public class Path {
	public ArrayList<Action> actions;
	public double cost;
	
	public Path() {
		actions = new ArrayList<>();
	}
	
	public Path(Action a, double cost) {
		actions = new ArrayList<Action>();
		actions.add(a);
		this.cost = cost;
	}
	
	public Path(ArrayList<Action> a, double cost) {
		actions = new ArrayList<Action>();
		actions.addAll(a);
		this.cost = cost;
	}
	public void addPath(Path path) { this.actions.addAll(path.actions); }
	
	public void add(Action a) {	actions.add(a); }
	
	public ArrayList<Path> successors(ArrayList<State> exclude) {
		ArrayList<Path> paths = new ArrayList<>();		
		for(Action a : lastRoad().actions()) {
			if(!exclude.contains(a.state)) {
				Path p = new Path(actions, cost + a.state.cost);
				p.add(a);
				paths.add(p);
			}
		}		
		return paths;
	}
	
	public boolean compare(State road) {
		return road.name.equals(lastRoad().name);
	}
	
	public State lastRoad() {
		return actions.get(actions.size() - 1).state;		
	}
	
	private static Path min(ArrayList<Path> paths) {
		Path best = paths.get(0);
		for(Path p : paths) {
			if(p.cost < best.cost) best = p;
		}
		return best;
	}
	
	public void print() {
		for(Action a : actions) {
			System.out.println(a.type + ": " + a.state.name);
		}
	}
	
	public static Path CFS(Road start, ArrayList<Road> goals) {		
		ArrayList<Path> open   = new ArrayList<>();
		ArrayList<Road> closed = new ArrayList<>();
		open.add(new Path(new Action(AT.S, start), 0));
		
		while(true) {			
			if(open.size() == 0) break;
			Path best = min(open);			
			for(Road g : goals) {
				if(best.compare(g)) {
					goals.remove(g);
					return best;
				}
			}
			open.remove(best);
			open.addAll(best.successors(closed));
			closed.add(best.lastRoad());			
		}		
		return null;
	}
}
