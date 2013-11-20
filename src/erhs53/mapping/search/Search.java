package erhs53.mapping.search;

import java.util.ArrayList;

import erhs53.mapping.AT;
import erhs53.mapping.Action;
import erhs53.mapping.Path;
import erhs53.mapping.Road;

public class Search {

	private static Path min(ArrayList<Path> paths) {
		Path best = paths.get(0);
		for(Path p : paths) {
			if(p.cost < best.cost) best = p;
		}
		return best;
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
