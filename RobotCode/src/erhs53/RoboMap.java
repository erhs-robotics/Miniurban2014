package erhs53;

import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;

public class RoboMap {
	
	public static MotorPort LEFT_MOTOR_PORT = MotorPort.B;
	public static MotorPort RIGHT_MOTOR_PORT = MotorPort.A;
	
	
	public static final float MAXSPEED = 2.4f * 360f;
	public static final float SLOWSPEED = 2f * 360f;
	public static final double WHEELDIAMETER = 3;
	public static final double TRACKWIDTH = 13;
	
	public static final long STOP_WAIT_TIME = 1;
	public static final long PARK_WAIT_TIME = 5;

	
	public static final double[] PARK_COUNTS = {0, 686.5, 1378.0, 2114.5, 0};

}