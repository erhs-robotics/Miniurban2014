package erhs53;

import java.io.IOException;

import javax.microedition.lcdui.Screen;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Settings;
import lejos.nxt.SystemSettings;
import lejos.nxt.comm.RConsole;
import lejos.robotics.Color;
import lejos.util.TextMenu;
import erhs53.control.FuzzyController;
import erhs53.mapping.Step;
import erhs53.utilities.ColorFilter;
import erhs53.utilities.Console;
import erhs53.utilities.MathUtils;
import erhs53.utilities.RobotSettings;

public class Miniurban2014 {
	private static Robot robot;

	private static void runProgram() {
		
		Step[] steps;
		try {
			steps = Step.loadSteps("steps.info");
		} catch (IOException e) {
			System.out.println("Failed to read map!");
			System.out.println(e.getMessage());
			return;
		}
		robot.followSteps(steps);
	}

	private static void calibrateColors() {
		//RConsole.openBluetooth(0);
		String[] colors = new String[] { "white", "black", "green", "blue",
				"red", "yellow" };

		for (String name : colors) {

			int[] red = new int[5];
			int[] green = new int[5];
			int[] blue = new int[5];

			Console.println("Config: " + name);
			Button.waitForAnyPress();
			for (int i = 0; i < 5; i++) {
				Color color = robot.outerRightColor.getColor();
				red[i] = color.getRed();
				green[i] = color.getGreen();
				blue[i] = color.getBlue();
				Console.println(red[i] + ", " + green[i] + ", " + blue[i]);
				Button.waitForAnyPress();
			}

			String meanRed = "" + MathUtils.mean(red);
			String meanGreen = "" + MathUtils.mean(green);
			String meanBlue = "" + MathUtils.mean(blue);
			RobotSettings.setSetting(name + ".r", meanRed);
			RobotSettings.setSetting(name + ".g", meanGreen);
			RobotSettings.setSetting(name + ".b", meanBlue);
		}

		Console.println("Calibration Successful");
		Console.println("System must now reboot...");
		Console.println("Press any key");
		Button.waitForAnyPress();
	}

	private static void testColorDetection() {
		//RConsole.openBluetooth(0);
		

		while (Button.readButtons() != Button.ID_ESCAPE) {
			Color c = robot.outerRightColor.getColor();
			int value = (int)(ColorFilter.white.evaluateAve(c) * 100);
			

			Console.println("Value: " + value);
			
		}
		RConsole.close();
	}

	private static void testFuzzyController() {
		FuzzyController controller = new FuzzyController();
		controller.followLine(robot);
	}

	private static void testStepLoader() {
		try {
			Step.loadSteps("steps.info");
			System.out.println("Steps loaded successfully!");
			Thread.sleep(1000);
		} catch (IOException e) {
			System.out.println("Failed to load steps!");
		} catch (InterruptedException e) {	}
		Button.waitForAnyPress();
	}

	public static void main(String[] args) {
		robot = new Robot();

		String[] items = new String[] { "Run program!", "Calibrate Colors",
				"Test Color Detection", "Test Fuzzy Controller",
				"Test Step Loader", "Exit" };
		TextMenu menu = new TextMenu(items, 1, "Miniurban 2014");
		
		int option;
		do {
			LCD.clear();
			option = menu.select();
			LCD.clear();
			switch (option) {
			case 0:
				runProgram();
				break;
			case 1:
				calibrateColors();
				return;
			case 2:
				testColorDetection();
				break;
			case 3:
				testFuzzyController();
				break;
			case 4:
				testStepLoader();
				break;
			}
		} while (option != 5);
	}
}
