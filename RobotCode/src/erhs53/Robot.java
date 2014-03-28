package erhs53;

import java.util.Arrays;
import java.util.Iterator;

import erhs53.control.FuzzyController;
import erhs53.mapping.GoalStep;
import erhs53.mapping.RoadStep;
import erhs53.mapping.RoadStep.Direction;
import erhs53.mapping.Step;
import erhs53.utilities.ColorFilter;
import erhs53.utilities.ColorHTSensorX;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.Color;
import lejos.robotics.navigation.DifferentialPilot;

public class Robot {
	
	// ======================================================
	// Constants
	// ======================================================
	
	public static final double WHEELDIAMETER  = 3;
	public static final double TRACKWIDTH     = 13.5;
	public static final float MAX_SPEED  = 600;
	public static final float SLOW_SPEED = 370;
	public static final float PARK_SPEED = 500;
	public static final float TURN_SPEED = 50; // In different units
	public static final float NEAR_TURN_TRAVEL_DISTANCE = 13;
	public static final float FAR_TURN_TRAVEL_DISTANCE = 15;
	public static final float FAR_TURN_RADIUS = 60;
	public static final float TURN_ANGLE = 90;
	public static final float CIRCLE_TURN_ANGLE = 70;
	
	
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
		pilot = new DifferentialPilot(WHEELDIAMETER, TRACKWIDTH, leftMotor, rightMotor);
		outerLeftColor = new ColorHTSensorX(SensorPort.S4);
		innerLeftColor = new ColorHTSensorX(SensorPort.S3);
		outerRightColor = new ColorHTSensorX(SensorPort.S1);
		innerRightColor = new ColorHTSensorX(SensorPort.S2);
		colorFilter = new ColorFilter();
		contoller = new FuzzyController(this);		
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
	
	public void turn(Direction dir, Direction side, boolean circle) {		
		float distance = dir == side ? NEAR_TURN_TRAVEL_DISTANCE : FAR_TURN_TRAVEL_DISTANCE;
		float turn = circle ? CIRCLE_TURN_ANGLE : TURN_ANGLE;
		float sign = dir == Direction.left ? 1 : -1;
		pilot.setTravelSpeed(TURN_SPEED);
		pilot.travel(distance);
		pilot.rotate(turn * sign);		
		pilot.stop();		
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
}
