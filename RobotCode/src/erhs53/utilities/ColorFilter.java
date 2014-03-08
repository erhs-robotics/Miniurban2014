package erhs53.utilities;

import erhs53.RoboMap;
import erhs53.control.MembershipFunction;
import erhs53.control.MembershipFunction.ComponentFunction;

public class ColorFilter {
	
	// ======================================================
	// Nested Types
	// ======================================================
	
	public static enum Color {
		WHITE, BLACK, BLUE, RED, GREEN, YELLOW, NONE
	}
	
	// ======================================================
	// Constants
	// ======================================================
	
	public static final MembershipFunction white;	
	
	// ======================================================
	// Static Initializer
	// ======================================================
	
	static {
		ComponentFunction whiteR = new ComponentFunction(RoboMap.WHITE_SIG[0], RoboMap.BLACK_SIG[0]);
		ComponentFunction whiteG = new ComponentFunction(RoboMap.WHITE_SIG[1], RoboMap.BLACK_SIG[1]);
		ComponentFunction whiteB = new ComponentFunction(RoboMap.WHITE_SIG[2], RoboMap.BLACK_SIG[2]);
		white = new MembershipFunction(whiteR, whiteG, whiteB);		
	}
	
	// ======================================================
	// Class Logic
	// ======================================================
	
	public static Color classify(lejos.robotics.Color color) {		
		float whiteValue = white.evaluateAve(color);
		float blackValue = 1 - MathUtils.max(whiteValue);
		int i = MathUtils.argMax(whiteValue, blackValue);
		return Color.values()[i];
		
	}	
}
