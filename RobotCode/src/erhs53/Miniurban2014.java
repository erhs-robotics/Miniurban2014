package erhs53;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import lejos.nxt.Button;
import erhs53.mapping.GoalStep;
import erhs53.mapping.RoadStep;
import erhs53.mapping.Step;
import erhs53.utilities.StringUtils;

public class Miniurban2014 {
	
	public static void main(String[] args) {
		Step[] steps;
		try {
			steps = Step.loadSteps("steps.info");
		} catch(IOException e) {
			System.out.println("Failed to read map!");
			System.out.println(e.getMessage());
		}
		Robot robot = new Robot();
		
		
		
	}	
}
