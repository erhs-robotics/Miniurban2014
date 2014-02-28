package erhs53.calibration;

import lejos.nxt.comm.RConsole;
import lejos.robotics.Color;
import erhs53.RoboMap;
import erhs53.Robot;
import erhs53.control.MembershipFunction;
import erhs53.control.MembershipFunction.ComponentFunction;

public class ColorTest {
	
	public static void main(String[] args) {
		RConsole.openBluetooth(0);
		
		Robot robot = new Robot();
		
		
		// Values for white
		ComponentFunction whiteR = new ComponentFunction(RoboMap._WHITE_SIG[0], RoboMap._BLACK_SIG[0]);
		ComponentFunction whiteG = new ComponentFunction(RoboMap._WHITE_SIG[1], RoboMap._BLACK_SIG[1]);
		ComponentFunction whiteB = new ComponentFunction(RoboMap._WHITE_SIG[2], RoboMap._BLACK_SIG[2]);
		MembershipFunction white = new MembershipFunction(whiteR, whiteG, whiteB);
		
		while(true) {
			Color c = robot.outerRightColor.getColor();
			double value = white.evaluate(c);
			double valueMagnitude = white.evaluateMagnitude(c);
			double valueAve = white.evaluateAve(c);
			RConsole.println("Value: " + value + ", valueMag: " 
							 + valueMagnitude + ", valueAve:" + valueAve);
		}
		
		
		
		
	}

}
