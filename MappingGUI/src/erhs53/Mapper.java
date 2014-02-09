package erhs53;

import erhs53.mapping.Map;
import erhs53.mapping.search.Action;
import erhs53.mapping.search.Path;

public class Mapper {
	static void printCodeArray(Path p) {
		System.out.printf("Action[] actions = new Action[%s];\n", actions.size());
		for(int i=0;i<p.actions.size();i++) {
			Action a = p.actions.get(i);
			System.out.printf("actions[%s] = new Action(%s, %s);\n", i, a.type, "Map." + a.state.name);
		}
	}
	public static void main(String[] args) {
		Map.G1.set(1, Map.PR);
		Map.G2.set(1, Map.PL);
		Map.G9.set(1, Map.PR);		
		
		Path p = Map.generatePath(Map.G1, Map.G2, Map.G9);		
		if(p == null) {
			System.out.println("Failed");
			return;
		}
		
		printCodeArray(p);

	}
}
