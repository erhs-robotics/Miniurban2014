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
	public static Road V1 = new Road("V1", 1), V2 = new Road("V2", 1),
			V3 = new Road("V3", 1), V4 = new Road("V4", 1), V5 = new Road("V5", 2),
			H1 = new Road("H1", 2), H2 = new Road("H2", 1), H3 = new Road("H3", 1), 
			H4 = new Road("H4", 1), H5 = new Road("H5", 1);
	
	/*****************************************************************************/
	
	/** GOAL LIST *****************************************************************/
	// Every goal in the map
	public static Goal G1, G2, G3, START, END;
	public static int goalNum = 4;
	/*****************************************************************************/
	
	/** MAP Generator ************************************************************/
	// Sets the actions available in each road
	static {
		V1.setActions(a(TR, H1));
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
		END = new Goal("END", Map.V2, 0);
		
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
