package erhs53;

import java.util.ArrayList;

import erhs53.mapping.Map;
import erhs53.mapping.search.Path;
import erhs53.mapping.search.State;
import erhs53.mapping.search.Path.Terminator;
import erhs53.mapping.Road;

public class Main {
	
	public static void main(String[] args) {
		Map.buildMap();		
		
		Terminator term = new Terminator() {			
			@Override
			public boolean isDone(Path path, ArrayList<State> closed) {
				return path.lastRoad() == Map.B1;
			}
		};
		
		Path p = Path.CFS(Map.ENTRY, term);		
		if(p == null) {
			System.out.println("Failed");
			return;
		}
		System.out.println(p.actions.get(0).type);
		p.print();
	}
	
}
