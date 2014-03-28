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
	
	public double getOutput(Color primaryColor, Color secondaryColor, float speed) {
		// membership functions
		float pWhite = ColorFilter.white.evaluateAve(primaryColor);		
		float pYellow = ColorFilter.yellow.evaluateAve(primaryColor);
		float pBlue = ColorFilter.blue.evaluateAve(primaryColor);
		float pRed = ColorFilter.red.evaluateAve(primaryColor);		
		float pFollowColor = MathUtils.max(pWhite, pYellow, pBlue, pRed);
		float pBlack = pFollowColor < 0.35 ? (1 - pWhite) : (1 - pFollowColor);		
		float pGreen = ColorFilter.green.evaluateAve(primaryColor);		
		
		// crisp output
		float out = 0;
		
		// if see black, turn towards line
		out += -speed/3.7 * pBlack;
		
		// if see white turn away from the line		
		out +=  speed/3.7 * pFollowColor;
		
		// if see green turn away from the line alot
		if(pGreen > 0.7)
			out = 200;
		
		
		if(secondaryColor != null) {
			float sWhite = ColorFilter.white.evaluateAve(secondaryColor);
			float sYellow = ColorFilter.yellow.evaluateAve(secondaryColor);
			float sFollowColor = MathUtils.max(sWhite, sYellow);
			sFollowColor = sFollowColor > 0.5 ? sFollowColor : 0;
			
			// if see white on the second color sensor, turn away alot alot
			//out += 300 * sFollowColor;
		}

		return out;
	}
	
	public void follow(ColorHTSensor outerSensor, ColorHTSensor innerSensor, Direction dir, float speed, boolean circle) {
		float bias = circle ? 125 : 0;
		double output;
		if(innerSensor != null)
			output = getOutput(outerSensor.getColor(), innerSensor.getColor(), speed);
		else
			output = getOutput(outerSensor.getColor(), null, speed);
		//RConsole.println("Output: " + output);
		
		if(output == output) {// make sure output is a number
			int sign = (dir == Direction.right) ? 1 : -1;
			robot.leftMotor.setSpeed(speed - (float)output * sign - bias);	
			robot.rightMotor.setSpeed(speed + (float)output * sign + bias);
		}
		robot.leftMotor.forward();
		robot.rightMotor.forward();
	}
	
	public void followLine(Direction dir, boolean slow, boolean circle) {
		ColorHTSensor outerSensor = (dir == Direction.left) ? robot.outerLeftColor : robot.outerRightColor;
		ColorHTSensor innerSensor = (dir == Direction.left) ? robot.innerLeftColor : robot.innerRightColor;
		ColorHTSensor stopSensor = (dir == Direction.left) ? robot.innerRightColor : robot.innerLeftColor;
		float speed = slow ? Robot.SLOW_SPEED : Robot.MAX_SPEED;
		
		while(ColorFilter.red.evaluateAve(stopSensor.getColor()) < 0.4f) {
			follow(outerSensor, innerSensor, dir, speed, circle);
		}
		//robot.leftMotor.setSpeed(0);
		//robot.rightMotor.setSpeed(0);
		robot.pilot.stop();	
		
	}	
}
