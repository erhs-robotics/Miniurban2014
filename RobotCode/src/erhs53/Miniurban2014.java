package erhs53;

import java.io.IOException;
import erhs53.mapping.Step;

public class Miniurban2014 {
	
	public static void main(String[] args) {
		Step[] steps;
		try {
			steps = Step.loadSteps("steps.info");
		} catch(IOException e) {
			System.out.println("Failed to read map!");
			System.out.println(e.getMessage());
			return;
		}
		Robot robot = new Robot();
		robot.followSteps(steps);
		
		
	}	
}
