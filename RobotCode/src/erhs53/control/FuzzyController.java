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
	
	// TODO combine getOutput into one function	
	public double getOutput(Color color) {
		// membership functions
		float white = ColorFilter.white.evaluateAve(color);		
		float yellow = ColorFilter.yellow.evaluateAve(color);
		float blue = ColorFilter.blue.evaluateAve(color);
		float followColor = MathUtils.max(white, yellow, blue);
		float black = (1 - followColor);		
		float green = ColorFilter.green.evaluateAve(color);
		
		
		// crisp output
		float out = 0;
		
		// if see black, turn towards line
		out += -100 * black;		
		
		// if see white turn away from the line		
		out +=  100 * followColor;
		

		return out;
	}
	
	public double getOutput(Color outerColor, Color innerColor) {
		// membership functions
		float outerWhite = ColorFilter.white.evaluateAve(outerColor);		
		float outerYellow = ColorFilter.yellow.evaluateAve(outerColor);
		float outerBlue = ColorFilter.blue.evaluateAve(outerColor);
		float outerRed = ColorFilter.red.evaluateAve(outerColor);
		float outerFollowColor = MathUtils.max(outerWhite, outerYellow, outerBlue, outerRed);
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
		out +=  200 * curve(outerFollowColor);
		
		// if see green turn away from the line alot
		//out +=  200 * greenMembership;
		
		// if see white on the second color sensor, turn away alot alot
		//out += 300 * innerFollowColor;
		Console.println("" + outerFollowColor);

		return out;
	}
	
	public void follow(ColorHTSensor outerSensor, ColorHTSensor innerSensor, Direction dir) {
		double output;
		if(innerSensor != null)
			output = getOutput(outerSensor.getColor(), innerSensor.getColor());
		else
			output = getOutput(outerSensor.getColor());
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
