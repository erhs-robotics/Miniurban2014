package erhs53;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import erhs53.mapping.Goal;
import erhs53.mapping.Map;
import erhs53.mapping.Road;
import erhs53.mapping.search.Action;
import erhs53.mapping.search.Path;
import erhs53.mapping.search.State;

public class Mapper {

	public static void writePath(Path path) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter("steps.info");
		for (Action a : path.actions) {
			State state = a.state;
			if (state instanceof Road) {
				Road road = (Road) state;
				fileOut.print("road,");
				fileOut.print(road.name + ",");
				fileOut.print(a.type + ",");
				fileOut.print(road.slow + ",");
				fileOut.println(road.circle);
			} else if (state instanceof Goal) {
				Goal goal = (Goal) state;
				fileOut.print("goal,");
				fileOut.print(goal.name + ",");
				fileOut.print(goal.space + ",");
				fileOut.println(goal.dir == Map.PL);
			}
		}
		fileOut.close();
	}

	public static void main(String[] args) throws IOException {		
		Map.G1.set(1, Map.PR);
		Map.G2.set(1, Map.PR);
		Map.G3.set(1, Map.PR);
		Map.G4.set(1, Map.PR);
		Map.G5.set(1, Map.PR);
		Map.G6.set(1, Map.PR);
		

		Path p = Map.generatePath(Map.G1, Map.G2, Map.G3, Map.G4, Map.G5, Map.G6);
		if (p == null) {
			System.out.println("Failed");
			return;
		}
		p.print();

		writePath(p);
	}
}
