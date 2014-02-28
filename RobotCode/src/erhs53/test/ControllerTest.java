package erhs53.test;

import lejos.nxt.comm.RConsole;
import erhs53.Robot;
import erhs53.control.FuzzyController;

public class ControllerTest {

	public static void main(String[] args) {
		
		//RConsole.openBluetooth(0);
		Robot robot = new Robot();
		FuzzyController controller = new FuzzyController();
		
		controller.followLine(robot);
		
	}
}
