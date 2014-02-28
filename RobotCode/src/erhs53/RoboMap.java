package erhs53;

import erhs53.utilities.BayesFilter.Gaus;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;

public class RoboMap {
	
	private static Gaus Gaus(float mean, float variance) {
		return new Gaus(mean, variance);
	}
	
	public static MotorPort LEFT_MOTOR_PORT   = MotorPort.B;
	public static MotorPort RIGHT_MOTOR_PORT  = MotorPort.A;	
	
	public static final float MAXSPEED        = 2.4f * 360f;
	public static final float SLOWSPEED       = 2f * 360f;
	public static final double WHEELDIAMETER  = 3;
	public static final double TRACKWIDTH     = 13;
	
	public static final long STOP_WAIT_TIME   = 1;
	public static final long PARK_WAIT_TIME   = 5;
	
	public static final Gaus[] WHITE_SIG      = new Gaus[] {Gaus(244.4f, 114.64f * 2000), Gaus(255f, 5f*2000), Gaus(255f, 5f*2000)};
	public static final Gaus[] RED_SIG        = new Gaus[] {Gaus(127.8f, 4.56f), Gaus(35.2f, 18.16f), Gaus(22.4f, 30.64f)};
	public static final Gaus[] BLUE_SIG       = new Gaus[] {Gaus(0, 0), Gaus(0, 0), Gaus(0, 0)};
	public static final Gaus[] BLACK_SIG      = new Gaus[] {Gaus(21f, 3.2f*10000), Gaus(26.6f, 4.24f*10000), Gaus(24f, 5.2f*10000)};
	public static final Gaus[] GREEN_SIG      = new Gaus[] {Gaus(0, 0), Gaus(0, 0), Gaus(0, 0)};
	public static final Gaus[] YELLOW_SIG     = new Gaus[] {Gaus(0, 0), Gaus(0, 0), Gaus(0, 0)};
	
	public static final float[] _WHITE_SIG = new float[] {250.4f, 255, 255};
	public static final float[] _BLACK_SIG = new float[] {23.2f, 30.2f, 24.8f};
	
	public static final double[] PARK_COUNTS  = {0, 686.5, 1378.0, 2114.5, 0};
	
	

}
