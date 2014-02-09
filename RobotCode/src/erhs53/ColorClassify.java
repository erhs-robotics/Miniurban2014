package erhs53;

import java.util.ArrayList;
import java.util.HashMap;

import lejos.nxt.Button;
import lejos.nxt.comm.RConsole;
import lejos.robotics.Color;

import erhs53.utilities.NaiveBayes;
import erhs53.utilities.NaiveBayes.Gaus;
import erhs53.utilities.NaiveBayes.Label;

public class ColorClassify {
	static final int R = 0, G = 1, B = 2;


	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		RConsole.openBluetooth(0);
		Robot robot = new Robot();
		RConsole.println("made robot");
		NaiveBayes filter = new NaiveBayes();
		RConsole.println("made filter");
		
		ArrayList<Gaus> whiteMap = new ArrayList<>();
		whiteMap.add(new Gaus(248.6, 4.44));
		whiteMap.add(new Gaus(255, .2));
		whiteMap.add(new Gaus(254, .36));
		RConsole.println("made white map");
		
		ArrayList<Gaus> blueMap = new ArrayList<>();
		blueMap.add(new Gaus(24.6, 52.44));
		blueMap.add(new Gaus(57.4, 47.84));
		blueMap.add(new Gaus(118.1, 83.89));
		RConsole.println("made blue map");
		
		Label white = new Label(0, whiteMap, 0.5);
		Label blue = new Label(1, blueMap, 0.5);
		RConsole.println("made  labels");		
		filter.addLabel(white); filter.addLabel(blue);
		RConsole.println("added labels");
		
		while(true) {
			//Button.waitForAnyPress();
			Color color = robot.outerRightColor.getColor();
			RConsole.println("got color");
			ArrayList<Double> z = new ArrayList<Double>();
			RConsole.println("made z map");
			z.add((double)color.getRed());
			z.add((double) color.getGreen());
			z.add((double) color.getBlue());
			RConsole.println("filled z map");
			RConsole.println(filter.classify(z) + "");
			RConsole.println("classified");
		}

	}

}
