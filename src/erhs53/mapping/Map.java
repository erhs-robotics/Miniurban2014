package erhs53.mapping;

public class Map {
	public static Road ENTRY = new Road("ENTRY", 0),
			L = new Road("L", 3),
			L2 = new Road("L2", 7),
			L3 = new Road("L3", 4),
			L4 = new Road("L4", 9),
			L5 = new Road("L5", 4),
			L6 = new Road("L6", 7),
			L7 = new Road("L7", 8),
			L3R = new Road("L3R", 2),
			L3RL = new Road("L3RL", 7),
			L3RL2 = new Road("L3RL2", 2),	
			A1 = new Road("A1", 0),
			A2 = new Road("A2", 1),
			A3 = new Road("A3", 2),
			B1 = new Road("B1", 0),
			B2 = new Road("B2", 1),
			B3 = new Road("B3", 2),
			C1 = new Road("C1", 0),
			C2 = new Road("C2", 1),
			C3 = new Road("C3", 2);
	
	public static void buildMap() {
		ENTRY.setActions(new Action(AT.TL, L));
		
		L.setActions(new Action(AT.TL, L2));
		L2.setActions(new Action(AT.TL, L3));
		L3.setActions(new Action(AT.TL, L4), new Action(AT.TR, L3R));
		L4.setActions(new Action(AT.TL, L5));
		L5.setActions(new Action(AT.TL, L6));
		L6 .setActions(new Action(AT.TL, L7), new Action(AT.PL, B1), 
					  new Action(AT.PL, B2), new Action(AT.PL, B3), new Action(AT.PR, C1),
					  new Action(AT.PR, C2), new Action(AT.PR, C3));
		L7.setActions(new Action(AT.F, ENTRY));
		L3R.setActions(new Action(AT.TL, L3RL));
		L3RL.setActions(new Action(AT.TL, L3RL2), new Action(AT.PL, A1), 
						new Action(AT.PL, A2), new Action(AT.PL, A3));
		L3RL2.setActions(new Action(AT.GS, L6));		
	}
}
