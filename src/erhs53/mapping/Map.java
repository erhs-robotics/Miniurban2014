package erhs53.mapping;

import java.util.ArrayList;

import erhs53.mapping.search.Action;
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
	public static int TL = 1;
	public static int TR = 2;
	public static int GS = 3;
	public static int PL = 4;
	public static int PR = 5;
	public static int S = 6;
	public static int F = 7;

	public static Road V1 = new Road("V1", 1), V2 = new Road("V2", 1),
			V3 = new Road("V3", 1), V4 = new Road("V4", 1), V5 = new Road("V5", 1),
			H1 = new Road("H1", 1), H2 = new Road("H2", 1), H3 = new Road("H3", 1), 
			H4 = new Road("H4", 1), H5 = new Road("H5", 1);
	
	public static Goal G1, G2, 
					   G3, 
					   START, END;
	public static Goal[] goals = {G1, G2, G3};

	private static Action a(int id, State r) {
		return new Action(id, r);
	}
	
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
		
		ArrayList<Action> actions = new ArrayList<>();
		actions.add(new Action(0, G1));
		actions.add(new Action(0, G2));
		actions.add(new Action(0, G3));
		START.actions = actions;
		
	}
	
	/**
	 * Defines every action you can take at each goal
	 * 
	 */
	public static void buildGoalMap(Goal... goals) {
		ArrayList<Action> actions = new ArrayList<>();
		for(Goal g : goals) {
			actions.add(a(0, g));
		}
		
		for(Goal g : goals) {
			ArrayList<Action> _actions = (ArrayList<Action>) actions.clone();
			_actions.remove(g);			
			g.actions = _actions;
		}
	}
	
}
