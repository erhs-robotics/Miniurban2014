package erhs53.control;

import lejos.nxt.comm.RConsole;
import lejos.robotics.Color;
import erhs53.RoboMap;
import erhs53.Robot;
import erhs53.control.MembershipFunction.ComponentFunction;
import erhs53.utilities.BayesFilter;
import erhs53.utilities.ColorFilter;
import erhs53.utilities.BayesFilter.Label;

public class FuzzyController {	
	private static final float MAX_SPEED = 500;
	MembershipFunction white;
	
	
	public FuzzyController() {		
		ComponentFunction whiteR = new ComponentFunction(RoboMap._WHITE_SIG[0], RoboMap._BLACK_SIG[0]);
		ComponentFunction whiteG = new ComponentFunction(RoboMap._WHITE_SIG[1], RoboMap._BLACK_SIG[1]);
		ComponentFunction whiteB = new ComponentFunction(RoboMap._WHITE_SIG[2], RoboMap._BLACK_SIG[2]);
		white = new MembershipFunction(whiteR, whiteG, whiteB);
		
		
	}
	
	private void normalize(double[] set) {
		double sum = 0;
		for(double x:set) sum += x;
		for(int i=0;i<set.length;i++)
			set[i] /= sum;	
	}

	public double getOutput(Color color) {
		// membership functions
		double whiteMembership = white.evaluateAve(color);
		
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
