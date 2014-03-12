package erhs53;

import erhs53.utilities.RobotSettings;
import lejos.nxt.MotorPort;
import lejos.nxt.Settings;

public class RoboMap {	
	public static MotorPort LEFT_MOTOR_PORT   = MotorPort.B;
	public static MotorPort RIGHT_MOTOR_PORT  = MotorPort.A;	
	
	public static final float MAXSPEED        = 2.4f * 360f;
	public static final float SLOWSPEED       = 2f * 360f;
	public static final double WHEELDIAMETER  = 3;
	public static final double TRACKWIDTH     = 13;
	
	public static final long STOP_WAIT_TIME   = 1;
	public static final long PARK_WAIT_TIME   = 5;
	
	public static final float[] WHITE_SIG  = getSig("white");
	public static final float[] GREEN_SIG  = getSig("green");
	public static final float[] YELLOW_SIG = getSig("yellow");
	public static final float[] RED_SIG    = getSig("red");
	public static final float[] BLUE_SIG   = getSig("blue");
	public static final float[] BLACK_SIG  = getSig("black");
	
	public static final double[] PARK_COUNTS  = {0, 686.5, 1378.0, 2114.5, 0};
	
	private static float[] getSig(String name) {
		float r = RobotSettings.getFloatSetting(name+".r", 0);
		float g = RobotSettings.getFloatSetting(name+".g", 0);
		float b = RobotSettings.getFloatSetting(name+".b", 0);
		
		return new float[]{r, g, b};
	}
}
