package erhs53;

import erhs53.mapping.Map;
import erhs53.mapping.search.Path;


public class Main {
	
	public static void main(String[] args) {
		
		Map.G1.set(1, Map.PR);
		Map.G2.set(1, Map.PL);
		Map.G9.set(1, Map.PR);		
		
		Path p = Map.generatePath(Map.G1, Map.G2, Map.G9);		
		if(p == null) {
			System.out.println("Failed");
			return;
		}
		
		p.print();
		
		
		//System.out.println(Map.generateCosts(Map.G1, Map.G2, Map.G3, Map.G4, Map.G5, Map.G6, Map.G7, Map.G8, Map.G9));
	}
	
}
