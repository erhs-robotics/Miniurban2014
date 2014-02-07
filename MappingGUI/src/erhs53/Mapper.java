package erhs53;

import erhs53.mapping.Map;
import erhs53.mapping.search.Path;

public class Mapper {
	
	public static void main(String[] args) {
		Map.G1.set(1, Map.PR);
		Map.G2.set(1, Map.PL);
		Map.G9.set(1, Map.PR);		
		
		Path p = Map.generatePath(Map.G1, Map.G2, Map.G9);		
		if(p == null) {
			System.out.println("Failed");
			return;
		}
		
		p.printCodeArray();

	}
}
