package erhs53.mapping;

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
	
	public ArrayList<Path> successors(ArrayList<Road> exclude) {
		ArrayList<Path> paths = new ArrayList<>();		
		for(Action a : lastRoad().actions) {
			if(!exclude.contains(a.road)) {
				Path p = new Path(actions, cost + a.road.cost());
				p.add(a);
				paths.add(p);
			}
		}		
		return paths;
	}
	
	public boolean compare(Road road) {
		return road.name.equals(lastRoad().name);
	}
	
	public Road lastRoad() {
		return actions.get(actions.size() - 1).road;		
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
			System.out.println(a.type.toString() + ": " + a.road.name);
		}
	}
	
	public static Path getPath(Road begin, ArrayList<Road> goals) {
		Path path = new Path();
		Road start = begin;
		while(goals.size() > 0) {
			path.addPath(CFS(start, goals));
			start = path.actions.get(path.actions.size() - 2).road;
		}
		goals.add(begin);
		path.addPath(CFS(start, goals));
		return path;
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
