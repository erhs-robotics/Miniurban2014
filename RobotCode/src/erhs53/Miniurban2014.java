package erhs53;

import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.Settings;
import lejos.nxt.comm.RConsole;
import lejos.robotics.Color;
import lejos.util.TextMenu;
import erhs53.control.FuzzyController;
import erhs53.mapping.Step;
import erhs53.utilities.ColorFilter;
import erhs53.utilities.MathUtils;

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
		RConsole.openBluetooth(0);
		String[] colors = new String[] { "white", "black", "green", "blue",
				"red", "yellow" };

		for (String name : colors) {

			int[] red = new int[5];
			int[] green = new int[5];
			int[] blue = new int[5];

			RConsole.println("Ready");
			Button.waitForAnyPress();
			for (int i = 0; i < 5; i++) {
				Color color = robot.outerRightColor.getColor();
				red[i] = color.getRed();
				green[i] = color.getGreen();
				blue[i] = color.getBlue();
				RConsole.println(red[i] + ", " + green[i] + ", " + blue[i]);
				Button.waitForAnyPress();
			}

			String meanRed = "" + MathUtils.mean(red);
			String meanGreen = "" + MathUtils.mean(green);
			String meanBlue = "" + MathUtils.mean(blue);
			Settings.setProperty(name + ".r", meanRed);
			Settings.setProperty(name + ".g", meanGreen);
			Settings.setProperty(name + ".b", meanBlue);
		}

		System.out.println("Calibration Successful");
		System.out.println("System must now reboot...");
		System.out.println("Press any key");
	}

	private static void testColorDetection() {
		RConsole.openBluetooth(0);

		while (Button.readButtons() != Button.ID_ENTER) {
			Color c = robot.outerRightColor.getColor();
			double value = ColorFilter.white.evaluateAve(c);

			RConsole.println("Value: " + value);
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
			RConsole.println("Steps loaded successfully!");
		} catch (IOException e) {
			System.out.println("Failed to load steps!");
		}
		Button.waitForAnyPress();
	}

	public static void main(String[] args) {
		robot = new Robot();

		String[] items = new String[] { "Run program!", "Calibrate Colors",
				"Test Color Detection", "Test Fuzzy Controller",
				"Test Step Loader", "Exit" };
		TextMenu menu = new TextMenu(items, 0, "Miniurban 2014");
		int option;
		do {
			switch (option = menu.select()) {
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
