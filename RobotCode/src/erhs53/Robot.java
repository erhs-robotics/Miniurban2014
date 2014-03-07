package erhs53;

import erhs53.utilities.ColorFilter;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class Robot {
	
	// ======================================================
	// Variables
	// ======================================================
	
	public final NXTRegulatedMotor leftMotor, rightMotor;	
	public final DifferentialPilot pilot;	
	public final ColorHTSensor outerLeftColor, innerLeftColor;
	public final ColorHTSensor outerRightColor;
	public final ColorHTSensor innerRightColor;
	public final ColorFilter colorFilter;	
	
	// ======================================================
	// Constructor
	// ======================================================
		
	public Robot() {
		leftMotor = new NXTRegulatedMotor(RoboMap.LEFT_MOTOR_PORT);
		rightMotor = new NXTRegulatedMotor(RoboMap.RIGHT_MOTOR_PORT);
		pilot = new DifferentialPilot(RoboMap.WHEELDIAMETER, RoboMap.TRACKWIDTH, leftMotor, rightMotor);
		outerLeftColor = new ColorHTSensor(SensorPort.S4);
		innerLeftColor = null;
		outerRightColor = new ColorHTSensor(SensorPort.S1);
		innerRightColor = new ColorHTSensor(SensorPort.S2);
		colorFilter = new ColorFilter();
		setSpeed(RoboMap.MAXSPEED);		
	}
	
	// ======================================================
	// Class Logic
	// ======================================================
	
	public double leftTachoCount() {
		return leftMotor.getTachoCount();
	}
	
	public double rightTachoCount() {
		return rightMotor.getTachoCount();
	}
	
	public double aveTachoCount() {
		return (leftTachoCount() + rightTachoCount()) / 2;
	}	
	
	public void stop() {
		pilot.stop();
	}
	
	public void setSpeed(float speed) {
		this.pilot.setTravelSpeed(speed);
	}	
}
