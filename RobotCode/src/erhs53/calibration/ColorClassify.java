package erhs53.calibration;

import lejos.nxt.Button;
import lejos.nxt.comm.RConsole;
import lejos.robotics.Color;
import erhs53.Robot;


public class ColorClassify {	
	public static void main(String[] args) {
		RConsole.openBluetooth(0);
		Robot robot = new Robot();
		
		while(Button.readButtons() == 0) {			
			Color color = robot.outerRightColor.getColor();			
			RConsole.println(robot.colorFilter.classify(color).name());			
		}
	}
}
