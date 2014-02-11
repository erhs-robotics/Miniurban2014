package erhs53.utilities;

import java.util.ArrayList;

import erhs53.RoboMap;

public class ColorFilter extends BayesFilter {
	public static enum Color {
		WHITE, BLACK, BLUE, RED, GREEN, YELLOW, NONE
	}

	public ColorFilter() {
		Label white  = new Label(Color.WHITE.ordinal(), RoboMap.WHITE_SIG, 1f/6);
		Label black  = new Label(Color.BLACK.ordinal(), RoboMap.BLACK_SIG, 1f/6);
		Label blue   = new Label(Color.BLUE.ordinal(), RoboMap.BLUE_SIG, 1f/6);
		Label red    = new Label(Color.RED.ordinal(), RoboMap.RED_SIG, 1f/6);
		Label green  = new Label(Color.GREEN.ordinal(), RoboMap.GREEN_SIG, 1f/6);
		Label yellow = new Label(Color.YELLOW.ordinal(), RoboMap.YELLOW_SIG, 1f/6);
		
		this.addLabel(white, black, blue, red, green, yellow);
	}
	
	public Color classify(lejos.robotics.Color color) {
		ArrayList<Double> z = new ArrayList<Double>(3);
		z.add((double)color.getRed());
		z.add((double) color.getGreen());
		z.add((double) color.getBlue());
		
		int c = this.classify(z);
		if(c == -1) return Color.NONE;
		return Color.values()[c];
	}
}
