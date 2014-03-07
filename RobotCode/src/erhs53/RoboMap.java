package erhs53;

import lejos.nxt.MotorPort;

public class RoboMap {	
	public static MotorPort LEFT_MOTOR_PORT   = MotorPort.B;
	public static MotorPort RIGHT_MOTOR_PORT  = MotorPort.A;	
	
	public static final float MAXSPEED        = 2.4f * 360f;
	public static final float SLOWSPEED       = 2f * 360f;
	public static final double WHEELDIAMETER  = 3;
	public static final double TRACKWIDTH     = 13;
	
	public static final long STOP_WAIT_TIME   = 1;
	public static final long PARK_WAIT_TIME   = 5;
	
	public static final float[] WHITE_SIG  = new float[] {250.4f, 255, 255};
	public static final float[] GREEN_SIG  = new float[] {26, 77.8f, 65};
	public static final float[] YELLOW_SIG = new float[] {0, 0, 0};
	public static final float[] RED_SIG    = new float[] {156.4f, 40.8f, 26};
	public static final float[] BLUE_SIG   = new float[] {0, 0, 0};
	public static final float[] BLACK_SIG  = new float[] {23.2f, 30.2f, 24.8f};
	
	public static final double[] PARK_COUNTS  = {0, 686.5, 1378.0, 2114.5, 0};
}
