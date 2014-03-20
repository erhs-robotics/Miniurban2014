package erhs53;

import java.io.IOException;
import java.util.Arrays;

import javax.microedition.lcdui.Screen;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Settings;
import lejos.nxt.SystemSettings;
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

	private static void testFuzzyController() {
		FuzzyController controller = new FuzzyController();
		controller.followLine(robot, Direction.right);
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
		RConsole.openBluetooth(0);
		RConsole.println("White: " + Arrays.toString(RoboMap.WHITE_SIG));
		RConsole.println("Black: " + Arrays.toString(RoboMap.BLACK_SIG));
		RConsole.println("Yellow: " + Arrays.toString(RoboMap.YELLOW_SIG));
		robot = new Robot();

		String[] items = new String[] { "Run program!", "Calibrate Color",
				"Test Color", "Test Controller",
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
		} while (option != -1);
	}
}
