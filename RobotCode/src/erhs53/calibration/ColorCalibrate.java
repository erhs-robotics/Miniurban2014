package erhs53.calibration;

import erhs53.Robot;
import lejos.nxt.Button;
import lejos.nxt.comm.RConsole;
import lejos.robotics.Color;

public class ColorCalibrate {

	static double mean(int[] data) {
		double sum = 0;
		for(int x : data) sum += x;
		return sum / data.length;
	}
	
	static double mean(double[] data) {
		double sum = 0;
		for(double x : data) sum += x;
		return sum / data.length;
	}
	
	static double variance(int[] data) {
		double mean = mean(data);
		double[] diffs = new double[data.length];
		for(int i=0;i<data.length;i++) {
			diffs[i] = Math.pow(data[i] - mean, 2);
		}
		return mean(diffs);		
	}
	
	public static void main(String[] args) {
		RConsole.openBluetooth(0);
		int[] red = new int[10];
		int[] green = new int[10];
		int[] blue = new int[10];
		
		Robot robot = new Robot();
		RConsole.println("Ready");
		Button.waitForAnyPress();
		for(int i=0;i<10;i++) {
			Color color = robot.outerRightColor.getColor();
			red[i] = color.getRed();
			green[i] = color.getGreen();
			blue[i] = color.getBlue();
			Button.waitForAnyPress();
			RConsole.println("Done with i = " + i);
		}
		
		
		RConsole.println("RED:");
		RConsole.println("Mean: " + mean(red));
		RConsole.println("Variance: " + variance(red));
		RConsole.println("----------------------------");
		
		RConsole.println("GREEN:");
		RConsole.println("Mean: " + mean(green));
		RConsole.println("Variance: " + variance(green));
		RConsole.println("----------------------------");
		
		RConsole.println("BLUE:");
		RConsole.println("Mean: " + mean(blue));
		RConsole.println("Variance: " + variance(blue));
		RConsole.println("----------------------------");
	}

}
