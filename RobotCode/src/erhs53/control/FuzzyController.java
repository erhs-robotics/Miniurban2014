package erhs53.control;

import lejos.nxt.ColorSensor;
import lejos.nxt.addon.ColorHTSensor;
import lejos.nxt.comm.RConsole;
import lejos.robotics.Color;
import erhs53.Robot;
import erhs53.mapping.RoadStep.Direction;
import erhs53.utilities.ColorFilter;
import erhs53.utilities.MathUtils;

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

	public double getOutput(Color outerColor, Color innerColor) {
		// membership functions
		float outerWhite = ColorFilter.white.evaluateAve(outerColor);		
		float outerYellow = ColorFilter.yellow.evaluateAve(outerColor);
		float outerFollowColor = MathUtils.max(outerWhite, outerYellow);
		float outerBlack = (1 - outerFollowColor);		
		float outerGreen = ColorFilter.green.evaluateAve(outerColor);
		
		float innerWhite = ColorFilter.white.evaluateAve(innerColor);
		float innerYellow = ColorFilter.yellow.evaluateAve(innerColor);
		float innerFollowColor = MathUtils.max(innerWhite, innerYellow);
		float innerBlack = (1 - innerFollowColor);
		
		
		// crisp output
		float out = 0;
		
		// if see black, turn towards line
		out += -100 * outerBlack;
		
		// if see white turn away from the line
		
		out +=  100 * outerFollowColor;
		
		// if see green turn away from the line alot
		//out +=  200 * greenMembership;
		
		// if see white on the second color sensor, turn away alot alot
		out += 300 * innerFollowColor;

		return out;
	}
	
	public void followLine(Robot robot, Direction dir) {
		ColorHTSensor outerSensor, innerSensor;
		outerSensor = (dir == Direction.left) ? robot.outerLeftColor : robot.outerRightColor;
		innerSensor = (dir == Direction.left) ? robot.innerLeftColor : robot.innerRightColor;
		
		while(true) {
			double output = getOutput(outerSensor.getColor(), innerSensor.getColor());
			RConsole.println("Output: " + output);
			
			if(output == output) {// make sure output is a number
				robot.leftMotor.setSpeed(MAX_SPEED - (float)output);	
				robot.rightMotor.setSpeed(MAX_SPEED + (float)output);
			}
			robot.leftMotor.forward();
			robot.rightMotor.forward();
		}
	}
}
