package erhs53.calibration;

import lejos.nxt.comm.RConsole;
import lejos.robotics.Color;
import erhs53.Robot;
import erhs53.utilities.ColorFilter;

public class ColorTest {
	
	public static void main(String[] args) {
		RConsole.openBluetooth(0);
		
		Robot robot = new Robot();	
		
		while(true) {
			Color c = robot.outerRightColor.getColor();
			double value = ColorFilter.white.evaluateAve(c);
			
			RConsole.println("Value: " + value);
		}		
	}
}
