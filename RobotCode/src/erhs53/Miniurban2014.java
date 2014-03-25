package erhs53;

import java.io.IOException;
import java.util.Arrays;

import javax.microedition.lcdui.Screen;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.Settings;
import lejos.nxt.SystemSettings;
import lejos.nxt.addon.ColorHTSensor;
import lejos.nxt.comm.RConsole;
import lejos.robotics.Color;
import lejos.util.TextMenu;
import erhs53.control.FuzzyController;
import erhs53.control.MembershipFunction;
import erhs53.mapping.Step;
import erhs53.mapping.RoadStep.Direction;
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
	
	private static void calibrateSensors() throws InterruptedException {
		Console.println("Init Black:");
		Console.println("Press Enter...");
		Button.waitForAnyPress();
		Thread.sleep(1000);
		robot.initBlackLevel();
		Console.println("Done!");
		
		Console.println("Init White:");
		Console.println("Press Enter...");
		Button.waitForAnyPress();
		Thread.sleep(1000);
		robot.initWhiteBalance();
		
		Console.println("Done!");
		
		Thread.sleep(1000);
		
	}

	private static void calibrateColors() throws InterruptedException {
		// RConsole.openBluetooth(0);
		String[] colors = new String[] { "white", "black", "green", "blue",
				"red", "yellow" };

		TextMenu menu = new TextMenu(colors, 1, "Calibrate Color");
		int index;
		while ((index = menu.select()) != -1) {
			LCD.clear();
			String name = colors[index];

			int[] red = new int[5];
			int[] green = new int[5];
			int[] blue = new int[5];

			Console.println("Config: " + name);
			Button.waitForAnyPress();
			Thread.sleep(1000);
			for (int i = 0; i < 5; i++) {
				Color color = robot.outerRightColor.getColor();
				red[i] = color.getRed();
				green[i] = color.getGreen();
				blue[i] = color.getBlue();
				Console.println(red[i] + ", " + green[i] + ", " + blue[i]);
				Button.waitForAnyPress();
				Thread.sleep(1000);
			}

			String meanRed = "" + MathUtils.mean(red);
			String meanGreen = "" + MathUtils.mean(green);
			String meanBlue = "" + MathUtils.mean(blue);
			RobotSettings.setSetting(name + ".r", meanRed);
			RobotSettings.setSetting(name + ".g", meanGreen);
			RobotSettings.setSetting(name + ".b", meanBlue);
			Console.println("Calibrated " + name);
			Thread.sleep(1000);
		}

		Console.println("Calibration Successful");
		Console.println("System must now reboot...");
		Console.println("Press any key");
		Button.waitForAnyPress();
	}

	private static void testColorDetection() {
		String[] colors = new String[] { "white", "green", "blue",
				"red", "yellow"};

		TextMenu menu = new TextMenu(colors, 1, "Test Color");

		int index;
		while ((index = menu.select()) != -1) {
			MembershipFunction colorFunction = null;
			switch(index) {
			case 0:
				colorFunction = ColorFilter.white;
				break;
			case 1:
				colorFunction = ColorFilter.green;
				break;
			case 2:
				colorFunction = ColorFilter.blue;
				break;
			case 3:
				colorFunction = ColorFilter.red;
				break;
			case 4:
				colorFunction = ColorFilter.yellow;
				break;
			}
			while (Button.readButtons() != Button.ID_ESCAPE) {
				Color c = robot.outerRightColor.getColor();
				int value = (int) (colorFunction.evaluateAve(c) * 100);

				Console.println("Value: " + value);

			}
		}
	}
	
	private static void testRawSensors() {
		String[] colors = new String[] { "1", "2", "3", "4"};

		TextMenu menu = new TextMenu(colors, 1, "From L to R");

		int index;
		while ((index = menu.select()) != -1) {
			ColorHTSensor sensor = null;
			switch(index) {
			case 0:
				sensor = robot.outerLeftColor;
				break;
			case 1:
				sensor = robot.innerLeftColor;
				break;
			case 2:
				sensor = robot.innerRightColor;
				break;
			case 3:
				sensor = robot.outerRightColor;
				break;			
			}
			while (Button.readButtons() != Button.ID_ESCAPE) {
				Color c = sensor.getColor();
				Console.println("<" + c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + ">");
			}
		}
	}

	private static void testFuzzyController() {
		FuzzyController controller = new FuzzyController(robot);
		controller.followLine(Direction.right);
	}

	private static void testStepLoader() {
		try {
			Step.loadSteps("steps.info");
			System.out.println("Steps loaded successfully!");
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Failed to load steps!");
			System.out.println(e.toString());
			
		} 
		Button.waitForAnyPress();
	}

	public static void main(String[] args) throws InterruptedException {
		//RConsole.openBluetooth(0);
		
		robot = new Robot();

		//TODO make connect Rconsole a menu item
		String[] items = new String[] { "Run program!", "Calibrate Sensors", "Calibrate Color",
				"Test Color", "Test Raw Sensor", "Test Controller",
				"Test Loader" };
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
				calibrateSensors();
				break;
			case 2:
				calibrateColors();
				return;
			case 3:
				testColorDetection();
				break;
			case 4:
				testRawSensors();
				break;
			case 5:
				testFuzzyController();
				break;
			case 6:
				testStepLoader();
				break;
			}
		} while (option != -1);
	}
}
