package erhs53.utilities;

import lejos.nxt.I2CPort;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.Color;

public class ColorHTSensorX extends ColorHTSensor {
	public ColorHTSensorX(I2CPort port) {
		super(port);		
	}
	
	@Override
	public Color getColor() {
		return new Color(getRGBComponent(Color.RED), getRGBComponent(Color.GREEN), getRGBComponent(Color.BLUE));
	}
}
