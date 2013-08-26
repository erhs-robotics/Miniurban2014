package erhs53;

import java.util.ArrayList;
import erhs53.mapping.Map;
import erhs53.mapping.Path;
import erhs53.mapping.Road;

public class Main {
	
	public static void main(String[] args) {
		Map.buildMap();		
		
		ArrayList<Road> goals = new ArrayList<>();
		goals.add(Map.B1);
		goals.add(Map.A3);
		
		Path p = Path.getPath(Map.ENTRY, goals);		
		if(p == null) {
			System.out.println("Failed");
			return;
		}
		System.out.println(p.actions.get(0).type);
		p.print();
	}
	
}
