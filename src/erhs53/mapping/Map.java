package erhs53.mapping;

import java.util.ArrayList;
import java.util.HashMap;

import erhs53.mapping.search.Action;
import erhs53.mapping.search.Path;
import erhs53.mapping.search.State;
/*         G2
 *        > >
 *        ^ V 
 *    + > + V
 * G1 ^   ^ V G3
 *    + < + V
 *      ^ 
 *      ^
 */



public class Map {
	// Types of actions
	public static int TL = 1; // Turn Left
	public static int TR = 2; // Turn Right
	public static int GS = 3; // Go Straight
	public static int PL = 4; // Park Left
	public static int PR = 5; // Park Right
	public static int S = 6;  // Start
	public static int F = 7;  // Finish

	/** ROAD LIST *****************************************************************/
	// Every road in the map
	/*public static Road V1 = new Road("V1", 1), V2 = new Road("V2", 1),
			V3 = new Road("V3", 1), V4 = new Road("V4", 1), V5 = new Road("V5", 2),
			H1 = new Road("H1", 2), H2 = new Road("H2", 1), H3 = new Road("H3", 1), 
			H4 = new Road("H4", 1), H5 = new Road("H5", 1);*/
	public static Road ENTRY = new Road("ENTRY", 0),
					   AV0 = new Road("AV0", 285),
					   AV1 = new Road("AV1", 100),
					   AV2 = new Road("AV2", 80),
					   AV3 = new Road("AV3", 60),
					   AV4 = new Road("AV4", 70),
					   AV5 = new Road("AV5", 50),
					   AV6 = new Road("AV6", 90),
					   AV7 = new Road("AV7", 70),
					   AV8 = new Road("AV8", 75),
					   AH0 = new Road("AH0", 210),
					   AH1 = new Road("AH1", 260),
					   AH2 = new Road("AH2", 180),
					   AH3 = new Road("AH3", 110),
					   AH4 = new Road("AH4", 265),
					   AH5 = new Road("AH5", 150),
					   AH7 = new Road("AH7", 120),
					   AH8 = new Road("AH8", 310),
					   AC0 = new Road("AC0", 85),
					   AC1 = new Road("AC1", 125),
					   AC2 = new Road("AC2", 80),
					  AC3B = new Road("AC3B", 75),
							 
					   BV0 = new Road("BV0", 50),
					   BV1 = new Road("BV1", 50),
					   BV2 = new Road("BV2", 50),
					   BV3 = new Road("BV3", 50),
					   BV4 = new Road("BV4", 90),
					   BV5 = new Road("BV5", 50),
					   BV6 = new Road("BV6", 90),
					   BV7 = new Road("BV7", 75),
					   BV8 = new Road("BV8", 20),
					   BV9 = new Road("BV9", 75),
					   BV10 = new Road("BV10", 75),
					   BV11 = new Road("BV11", 75),
					   BV12 = new Road("BV12", 75),
					   BV13 = new Road("BV13", 75),
					   BH0 = new Road("BH0", 210),
					   BH1 = new Road("BH1", 150),
					   BH2 = new Road("BH2", 105),
					   BH3 = new Road("BH3", 110),
					   BH4 = new Road("BH4", 150),
					   BH5 = new Road("BH5", 110),
					   BH6 = new Road("BH6", 65),
					   BH7 = new Road("BH7", 110),
					   BH8 = new Road("BH8", 105),
					   BH9 = new Road("BH9", 110),
					   BH10 = new Road("BH10", 150),
					   BH11 = new Road("BH11", 180),
					   BH12 = new Road("BH12", 165),
							 
					   CV0 = new Road("CV0", 50),
					   CV1 = new Road("CV1", 50),
					   CV2 = new Road("CV2", 210),
					   CV3 = new Road("CV3", 165),
					   CV4 = new Road("CV4", 165),
					   CV5 = new Road("CV5", 75),
					   CV7 = new Road("CV7", 75),
					   CV8 = new Road("CV8", 75),
					   CV9 = new Road("CV9", 75),
					   CV10 = new Road("CV10", 75),
					   CH0 = new Road("CH0", 160),
					   CH1 = new Road("CH1", 205),
					   CH2 = new Road("CH2", 160),
					   CH3 = new Road("CH3", 115),
					   CH4 = new Road("CH4", 90),
					   CH5 = new Road("CH5", 70),
					   CH6 = new Road("CH6", 90),
					   CH7 = new Road("CH7", 90),
					   CH8 = new Road("CH8", 115),
					   CH9 = new Road("CH9", 90),
					   CH11 = new Road("CH11", 180),
					   CH12 = new Road("CH12", 90);
							 
	/*****************************************************************************/
	
	/** GOAL LIST *****************************************************************/
	// Every goal in the map
	public static Goal G1, G2, G3, START, END;
	public static int goalNum = 4;
	/*****************************************************************************/
	
	/** MAP Generator ************************************************************/
	// Sets the actions available in each road
	static {
		/*V1.setActions(a(TR, H1));
		V2.setActions(a(TL, H2));
		V3.setActions(a(TR, H4));
		V4.setActions(a(GS, V3));
		V5.setActions(a(TR, H5));
		
		H1.setActions(a(TL, V3));
		H2.setActions(a(TR, V1));
		H3.setActions(a(GS, H2), a(F, V2));
		H4.setActions(a(TR, V5));
		H5.setActions(a(GS, H3), a(TR, V4));
		
		G1 = new Goal("G1", Map.V1, 0);
		G2 = new Goal("G2", Map.H4, 0);
		G3 = new Goal("G3", Map.V5, 0); 
		START = new Goal("START", Map.V2, 0);
		END = new Goal("END", Map.V2, 0);*/
		
		//Section A
		AH0.setActions(a(TL, AV1));
		AH1.setActions(a(TL, AV1), a(GS, AH0));
		AH2.setActions(a(GS, BH2), a(TR, AV6));
		AH3.setActions(a(TL, AV4));
		AH4.setActions(a(GS, BH8), a(TR, AV8));
		AH5.setActions(a(GS, AH7), a(TL, AC2));
		AH7.setActions(a(GS, AH8));
		AH8.setActions(a(TL, BV10), a(GS, BH11));
		AV0.setActions(a(TL, AH5));
		AV1.setActions(a(GS, AV2), a(TL, AC1));
		AV2.setActions(a(TL, AH4), a(GS, AV3));
		AV3.setActions(a(TL, AC3B));
		AV4.setActions(a(TL, AH4));
		AV5.setActions(a(GS, AV6), a(TL, BH2));
		AV7.setActions(a(GS, AV8), a(TL, BH8));
		AV8.setActions(a(TL, AH8));
		AC0.setActions(a(GS, AV1));
		AC1.setActions(a(GS, AC0), a(TR, AH2));
		AC2.setActions(a(GS, AC3B));
		AC3B.setActions(a(TL, AH8));
		//Section B
		BH0.setActions(a(TL, AV5), a(GS, AH1));
		BH1.setActions(a(GS, BH0), a(TL, BV1));
		BH2.setActions(a(TL, BV0), a(GS, BH3));
		BH3.setActions(a(TR, BV5), a(GS, BH4));
		
		
		
	}
	
	/*****************************************************************************/
	
	/**
	 * Convenience function that creates a new Action object
	 * @param id
	 * @param r
	 * @return
	 */
	private static Action a(int id, State r) {
		return new Action(id, r);
	}
	
	/**
	 * Defines every action you can take at each goal
	 * 
	 */
	public static void buildGoalMap(Goal... goals) {
		goalNum = goals.length;
		ArrayList<Action> actions = new ArrayList<>();
		for(Goal g : goals) {
			actions.add(a(0, g));
		}
		START.actions = actions;
		
		for(Goal g : goals) {
			@SuppressWarnings("unchecked")
			ArrayList<Action> _actions = (ArrayList<Action>) actions.clone();
			_actions.remove(g);			
			g.actions = _actions;
		}
	}
	
	/**
	 * Used to generate the actual path followed that will visit each goal.
	 * First examines the optimal order in which to visit goals, then finds each
	 * path for the goals and concatenates the separate paths together.
	 * @param goals The goals we need to visit
	 * @return The optimal path from the start, to each goal, and back again
	 */
	public static Path generatePath(Goal... goals) {
		Path path = new Path(); // Will store the final path
		buildGoalMap(goals); // Define the actions available for each goal
		Path goalPath = Path.CFS(START, END); // Find the optimal order to visit goals
		// For each goal, find the path to the next goal
		for(int i=0;i<goalPath.length()-1;i++) {
			Goal g1 = (Goal)goalPath.get(i).state; // The goal we start from
			Goal g2 = (Goal)goalPath.get(i+1).state; // The goal we end on
			Path pathPart = Path.CFS(g1.road, g2.road); // Generate the path from g1 to g2
			path.addPath(pathPart); // Append the path to our final path
			path.add(new Action(g2.dir, g2)); // Add the direction to park			
		}
		
		return path;
	}
	
	public static String generateCosts(Goal... goals) {
		String declaration = "HashMap<String, Double> %s = new HashMap<>();\n";
		String keyValue = "%s.put(\"%s\", %s);\n";
		String result = "";
		for(Goal start : goals) {
			result += String.format(declaration, start.name);
			for(Goal end : goals) {
				if(start == end) continue;				
				Path p = Path.CFS(start.road, end.road);
				result += String.format(keyValue, start.name, end.name, p.cost);				
			}
			// Also find cost to finish
			Path p = Path.CFS(start.road, END.road);
			result += String.format(keyValue, start.name, END.name, p.cost);
			result += "\n";
		}
		// Also find the cost from start to each goal
		result += String.format(declaration, START.name);
		for(Goal end : goals) {
			Path p = Path.CFS(START.road, end.road);
			result += String.format(keyValue, START.name, end.name, p.cost);
		}
		// Finish up by appending all new hashmaps to the master map
		result += "\nCOST = new HashMap<>();\n";
		for(Goal g : goals) {
			result += String.format(keyValue, "COST", g.name, g.name);
		}
		// Don't forget the start
		result += String.format(keyValue, "COST", START.name, START.name);		
		
		
		return result;
	}
	
}
