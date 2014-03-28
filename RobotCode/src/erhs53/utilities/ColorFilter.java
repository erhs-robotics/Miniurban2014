package erhs53.utilities;

import lejos.robotics.Color;
import erhs53.RoboMap;
import erhs53.control.MembershipFunction;
import erhs53.control.MembershipFunction.ComponentFunction;

public class ColorFilter {
	
	// ======================================================
	// Constants
	// ======================================================
	
	public static final MembershipFunction white;
	public static final MembershipFunction red;	
	public static final MembershipFunction yellow;
	public static final MembershipFunction green;
	public static final MembershipFunction blue;
	public static final MembershipFunction black; // based only on (1 - white)!!
	
	// ======================================================
	// Static Initializer
	// ======================================================
	
	static {
		ComponentFunction whiteR = new ComponentFunction(RoboMap.WHITE_SIG[0], RoboMap.BLACK_SIG[0]);
		ComponentFunction whiteG = new ComponentFunction(RoboMap.WHITE_SIG[1], RoboMap.BLACK_SIG[1]);
		ComponentFunction whiteB = new ComponentFunction(RoboMap.WHITE_SIG[2], RoboMap.BLACK_SIG[2]);
		white = new MembershipFunction(whiteR, whiteG, whiteB);
		
		ComponentFunction redR = new ComponentFunction(RoboMap.RED_SIG[0], RoboMap.BLACK_SIG[0]);
		ComponentFunction redG = new ComponentFunction(RoboMap.RED_SIG[1], RoboMap.BLACK_SIG[1]);
		ComponentFunction redB = new ComponentFunction(RoboMap.RED_SIG[2], RoboMap.BLACK_SIG[2]);
		red = new MembershipFunction(redR, redG, redB);
				
		ComponentFunction yellowR = new ComponentFunction(RoboMap.YELLOW_SIG[0], RoboMap.BLACK_SIG[0]);
		ComponentFunction yellowG = new ComponentFunction(RoboMap.YELLOW_SIG[1], RoboMap.BLACK_SIG[1]);
		ComponentFunction yellowB = new ComponentFunction(RoboMap.YELLOW_SIG[2], RoboMap.BLACK_SIG[2]);
		yellow = new MembershipFunction(yellowR, yellowG, yellowB);
		
		ComponentFunction greenR = new ComponentFunction(RoboMap.GREEN_SIG[0], RoboMap.BLACK_SIG[0]);
		ComponentFunction greenG = new ComponentFunction(RoboMap.GREEN_SIG[1], RoboMap.BLACK_SIG[1]);
		ComponentFunction greenB = new ComponentFunction(RoboMap.GREEN_SIG[2], RoboMap.BLACK_SIG[2]);
		green = new MembershipFunction(greenR, greenG, greenB);
		
		ComponentFunction blueR = new ComponentFunction(RoboMap.BLUE_SIG[0], RoboMap.BLACK_SIG[0]);
		ComponentFunction blueG = new ComponentFunction(RoboMap.BLUE_SIG[1], RoboMap.BLACK_SIG[1]);
		ComponentFunction blueB = new ComponentFunction(RoboMap.BLUE_SIG[2], RoboMap.BLACK_SIG[2]);
		blue = new MembershipFunction(blueR, blueG, blueB);
		
		
		black = new MembershipFunction() {
			@Override
			public float evaluateAve(Color color) {				
				return 1 - white.evaluateAve(color);
			}
		};
	}
	
	// ======================================================
	// Class Logic
	// ======================================================
	
		
}
