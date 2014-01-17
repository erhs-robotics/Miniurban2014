package erhs53;

import erhs53.mapping.Map;
import erhs53.mapping.search.Path;


public class Main {
	
	public static void main(String[] args) {	
		Map.G1.space = 1;
		Map.G2.space = 2;
		Map.G3.space = 3;
		Map.buildGoalMap(Map.G1, Map.G2, Map.G3);
		
		Path p = Path.CFS(Map.START, Map.END);		
		if(p == null) {
			System.out.println("Failed");
			return;
		}
		
		p.print();
	}
	
}
