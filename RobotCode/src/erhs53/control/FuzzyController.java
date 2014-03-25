package erhs53.control;

import lejos.nxt.ColorSensor;
import lejos.nxt.addon.ColorHTSensor;
import lejos.nxt.comm.RConsole;
import lejos.robotics.Color;
import erhs53.Robot;
import erhs53.mapping.RoadStep.Direction;
import erhs53.utilities.ColorFilter;
import erhs53.utilities.Console;
import erhs53.utilities.MathUtils;

public class FuzzyController {	
	
	// ======================================================
	// Variables
	// ======================================================
	
	private static final float MAX_SPEED = 500;
	private final Robot robot;
	
	
	// ======================================================
	// Constructor
	// ======================================================
	
	public FuzzyController(Robot robot) {
		this.robot = robot;			
	}
	
	// ======================================================
	// Class Logic
	// ======================================================
	
	private float curve(float x) {
		float p = 0.8f;
		if(x > p) {
			return MathUtils.map(x, p, 1f, 0.8f, 1f);
		} else {
			return MathUtils.map(x, 0f, p, 0f, 0.5f);
		}		
	}
	
	public double getOutput(Color primaryColor, Color secondaryColor) {
		// membership functions
		float pWhite = ColorFilter.white.evaluateAve(primaryColor);		
		float pYellow = ColorFilter.yellow.evaluateAve(primaryColor);
		float pBlue = ColorFilter.blue.evaluateAve(primaryColor);
		float pRed = ColorFilter.red.evaluateAve(primaryColor);		
		float sfollowColor = MathUtils.max(pWhite, pYellow, pBlue, pRed);
		float pBlack = (1 - sfollowColor);		
		float pGreen = ColorFilter.green.evaluateAve(primaryColor);		
		
		// crisp output
		float out = 0;
		
		// if see black, turn towards line
		out += -100 * pBlack;		
		
		// if see white turn away from the line		
		out +=  200 * curve(sfollowColor);
		
		// if see green turn away from the line alot
		//out +=  200 * greenMembership;
		
		// if see white on the second color sensor, turn away alot alot
		//out += 300 * innerFollowColor;
		Console.println("" + sfollowColor);
		
		if(secondaryColor != null) {
			float sWhite = ColorFilter.white.evaluateAve(secondaryColor);
			float sYellow = ColorFilter.yellow.evaluateAve(secondaryColor);
			float sFollowColor = MathUtils.max(sWhite, sYellow);
			float innerBlack = (1 - sFollowColor);
		}

		return out;
	}
	
	public void follow(ColorHTSensor outerSensor, ColorHTSensor innerSensor, Direction dir) {
		double output;
		if(innerSensor != null)
			output = getOutput(outerSensor.getColor(), innerSensor.getColor());
		else
			output = getOutput(outerSensor.getColor(), null);
		//RConsole.println("Output: " + output);
		
		if(output == output) {// make sure output is a number
			int sign = (dir == Direction.right) ? 1 : -1;
			robot.leftMotor.setSpeed(MAX_SPEED - (float)output * sign);	
			robot.rightMotor.setSpeed(MAX_SPEED + (float)output * sign);
		}
		robot.leftMotor.forward();
		robot.rightMotor.forward();
	}
	
	public void followLine(Direction dir) {
		ColorHTSensor outerSensor = (dir == Direction.left) ? robot.outerLeftColor : robot.outerRightColor;
		ColorHTSensor innerSensor = (dir == Direction.left) ? robot.innerLeftColor : robot.innerRightColor;
		
		while(true) {
			follow(outerSensor, innerSensor, dir);
		}
	}
	
	// ======================================================
	// Parking Logic
	// ======================================================
	
	public void driveToSpace(int space, Direction dir) {
		ColorHTSensor outerSensor = (dir == Direction.left) ? robot.outerLeftColor : robot.outerRightColor;
		ColorHTSensor innerSensor = (dir == Direction.left) ? robot.innerLeftColor : robot.innerRightColor;
		ColorFilter.Color searchColor = ColorFilter.Color.WHITE;
		for(int i=0;i<space*2 - 1;) {
			follow(innerSensor, null, dir);
			//if(ColorFilter.classify(outerSensor.getColor()) == searchColor) {
				//i++;
				//searchColor = (searchColor == ColorFilter.Color.WHITE) ? 
				//				ColorFilter.Color.BLACK : ColorFilter.Color.WHITE;
			//}
			Console.println("" + i);			
		}
		
		robot.leftMotor.stop();
		robot.rightMotor.stop();	
	}
	
	public void park(Direction dir) {
		// Drive until there is a blue line
		// Find the correct space
		// Park in the space
		// Reverse out of park
		// drive to the end of the road
	}}
