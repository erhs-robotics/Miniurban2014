package erhs53;

import erhs53.utilities.RobotSettings;
import lejos.nxt.MotorPort;
import lejos.nxt.Settings;

public class RoboMap {	
	
	public static MotorPort LEFT_MOTOR_PORT   = MotorPort.B;
	public static MotorPort RIGHT_MOTOR_PORT  = MotorPort.A;	
	
	// Color Signatures
	// 		a signature is a 3 tuple representing the mean r, g, and b values for a color
	public static final float[] WHITE_SIG  = getSig("white");
	public static final float[] GREEN_SIG  = getSig("green");
	public static final float[] YELLOW_SIG = getSig("yellow");
	public static final float[] RED_SIG    = getSig("red");
	public static final float[] BLUE_SIG   = getSig("blue");
	public static final float[] BLACK_SIG  = getSig("black");	
	
	private static float[] getSig(String name) {
		float r = RobotSettings.getFloatSetting(name+".r", 0);
		float g = RobotSettings.getFloatSetting(name+".g", 0);
		float b = RobotSettings.getFloatSetting(name+".b", 0);
		
		return new float[]{r, g, b};
	}
}
