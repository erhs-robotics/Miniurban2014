package erhs53.control;

import lejos.nxt.comm.RConsole;
import lejos.robotics.Color;
import erhs53.Robot;
import erhs53.utilities.ColorFilter;

public class FuzzyController {	
	
	// ======================================================
	// Variables
	// ======================================================
	
	private static final float MAX_SPEED = 500;
	
	// ======================================================
	// Variables
	// ======================================================
	
	public FuzzyController() {
			
	}
	
	// ======================================================
	// Class Logic
	// ======================================================

	public double getOutput(Color color) {
		// membership functions
		double whiteMembership = ColorFilter.white.evaluateAve(color);
		
		// crisp output
		double out = 0;
		out += 50 * (1 - whiteMembership);// (black) positive response
		out += -200 * whiteMembership;// negative response	

		return out;
	}
	
	public void followLine(Robot robot) {		
		while(true) {
			double output = getOutput(robot.outerRightColor.getColor());
			RConsole.println("Output: " + output);
			
			if(output == output) {// if not a number
				robot.leftMotor.setSpeed(MAX_SPEED - (float)output);	
				robot.rightMotor.setSpeed(MAX_SPEED + (float)output);
			}
			robot.leftMotor.forward();
			robot.rightMotor.forward();
		}
	}
}
