package erhs53;

import erhs53.utilities.MathUtils;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class Robot {
	
	private final NXTRegulatedMotor leftMotor, rightMotor;	
	public DifferentialPilot pilot;
	
	public ColorHTSensor outerLeftColor, innerLeftColor, outerRightColor, innerRightColor;
	private float speed = 400;
	
		
	public Robot() {
		leftMotor = new NXTRegulatedMotor(RoboMap.LEFT_MOTOR_PORT);
		rightMotor = new NXTRegulatedMotor(RoboMap.RIGHT_MOTOR_PORT);
		pilot = new DifferentialPilot(RoboMap.WHEELDIAMETER, RoboMap.TRACKWIDTH, leftMotor, rightMotor);
		outerLeftColor = new ColorHTSensor(SensorPort.S4);
		innerLeftColor = null;
		outerRightColor = new ColorHTSensor(SensorPort.S1);
		innerRightColor = new ColorHTSensor(SensorPort.S2);
		setSpeed(RoboMap.MAXSPEED);		
	}
	
	public double leftTachoCount() {
		return leftMotor.getTachoCount();
	}
	
	public double rightTachoCount() {
		return rightMotor.getTachoCount();
	}
	
	public double aveTachoCount() {
		return (leftTachoCount() + rightTachoCount()) / 2;
	}	
	
	/* Basic Driving Code ****************************************************/
	public void tankDrive(double left, double right) {
		left = MathUtils.clamp(left, -1, 1);
		right = MathUtils.clamp(right, -1, 1);
		
		float leftDrive = (float) left;
		float rightDrive = (float) right;
	
		leftMotor.setSpeed(speed * leftDrive);
		rightMotor.setSpeed(speed * rightDrive);
		leftMotor.forward();
		rightMotor.forward();
	}
	
	public void stop() {
		pilot.stop();
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
		this.pilot.setTravelSpeed(speed);
	}	
}
