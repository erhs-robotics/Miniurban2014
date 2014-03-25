package erhs53;

import java.util.Arrays;
import java.util.Iterator;

import erhs53.control.FuzzyController;
import erhs53.mapping.GoalStep;
import erhs53.mapping.RoadStep;
import erhs53.mapping.Step;
import erhs53.utilities.ColorFilter;
import erhs53.utilities.ColorHTSensorX;
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
	public final ColorHTSensorX outerLeftColor, innerLeftColor;
	public final ColorHTSensorX outerRightColor, innerRightColor;
	public final ColorFilter colorFilter;
	public final FuzzyController contoller;
	
	// ======================================================
	// Constructor
	// ======================================================
		
	public Robot() {
		leftMotor = new NXTRegulatedMotor(RoboMap.LEFT_MOTOR_PORT);
		rightMotor = new NXTRegulatedMotor(RoboMap.RIGHT_MOTOR_PORT);
		pilot = new DifferentialPilot(RoboMap.WHEELDIAMETER, RoboMap.TRACKWIDTH, leftMotor, rightMotor);
		outerLeftColor = new ColorHTSensorX(SensorPort.S4);
		innerLeftColor = new ColorHTSensorX(SensorPort.S3);
		outerRightColor = new ColorHTSensorX(SensorPort.S1);
		innerRightColor = new ColorHTSensorX(SensorPort.S2);
		colorFilter = new ColorFilter();
		contoller = new FuzzyController(this);
		setSpeed(RoboMap.MAXSPEED);		
		
		
		
	}
	
	// ======================================================
	// Class Logic
	// ======================================================
	
	public void initBlackLevel() {
		outerRightColor.initBlackLevel();		
		innerRightColor.initBlackLevel();		
		outerLeftColor.initBlackLevel();		
		innerLeftColor.initBlackLevel();
	}
	
	public void initWhiteBalance() throws InterruptedException {
		outerRightColor.initWhiteBalance();		
		innerRightColor.initWhiteBalance();
		outerLeftColor.initWhiteBalance();		
		innerLeftColor.initWhiteBalance();		
	}
	
	public void followSteps(Step[] steps) {		
		for(int i=0;i<steps.length;i++) {
			if(steps[i] instanceof RoadStep) {
				RoadStep roadStep = (RoadStep) steps[i];
				// execute turn
				// follow line until the end of the road				
			} else if(steps[i] instanceof GoalStep) {
				GoalStep goalStep = (GoalStep) steps[i];
				// Follow the correct side until the parking lot
				// once at the start of the parking lot, drive to correct spot
				// park wait
				// get out of the parking spot
				// follow the line until the stop sign
			}
		}		
	}	
	
	// ======================================================
	// Getters and Setters
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
