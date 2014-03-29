package erhs53;

import java.util.Arrays;
import java.util.Iterator;

import erhs53.control.FuzzyController;
import erhs53.control.MembershipFunction;
import erhs53.mapping.GoalStep;
import erhs53.mapping.RoadStep;
import erhs53.mapping.RoadStep.Direction;
import erhs53.mapping.Step;
import erhs53.utilities.ColorFilter;
import erhs53.utilities.ColorHTSensorX;
import erhs53.utilities.Console;
import erhs53.utilities.MovingAverage;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.Color;
import lejos.robotics.navigation.DifferentialPilot;

public class Robot {

	public enum TurnType {
		normal, straight, ontoCircle, stayOnCircle, offCircle;
		private static final float[] angles = new float[] { 90, 0, 70, 56.5f, 50 };
		private static final float[] dists = new float[] { 14, 20, 14, 15, 21 };

		public float getAngle() {
			return angles[this.ordinal()];
		}

		public float getDist() {
			return dists[this.ordinal()];
		}
	}

	// ======================================================
	// Constants
	// ======================================================

	public static final double WHEELDIAMETER = 3;
	public static final double TRACKWIDTH = 13.5;
	public static final float MAX_SPEED = 525;
	public static final float SLOW_SPEED = 370;
	public static final float PARK_SPEED = 400;
	public static final float TURN_SPEED = 50; // In different units
	public static final float NEAR_TURN_TRAVEL_DISTANCE = 13;
	public static final float FAR_TURN_TRAVEL_DISTANCE = 15;
	public static final float FAR_TURN_RADIUS = 60;
	public static final float TURN_ANGLE = 90;
	public static final float ONTO_CIRCLE_TURN_ANGLE = 70;

	// ======================================================
	// Variables
	// ======================================================

	public final NXTRegulatedMotor leftMotor, rightMotor;
	public final DifferentialPilot pilot;
	public final ColorHTSensorX outerLeftColor, innerLeftColor;
	public final ColorHTSensorX outerRightColor, innerRightColor;
	public final ColorFilter colorFilter;
	public final FuzzyController controller;

	// ======================================================
	// Constructor
	// ======================================================

	public Robot() {
		leftMotor = new NXTRegulatedMotor(RoboMap.LEFT_MOTOR_PORT);
		rightMotor = new NXTRegulatedMotor(RoboMap.RIGHT_MOTOR_PORT);
		pilot = new DifferentialPilot(WHEELDIAMETER, TRACKWIDTH, leftMotor,
				rightMotor);
		outerLeftColor = new ColorHTSensorX(SensorPort.S4);
		innerLeftColor = new ColorHTSensorX(SensorPort.S3);
		outerRightColor = new ColorHTSensorX(SensorPort.S1);
		innerRightColor = new ColorHTSensorX(SensorPort.S2);
		colorFilter = new ColorFilter();
		controller = new FuzzyController(this);
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
		
		// move out of the initial position
		controller.followLine(Direction.left, false, false);
		turn(Direction.left, TurnType.normal);

		for (int i = 0; i < steps.length - 2; i++) {
			if (steps[i] instanceof RoadStep) {
				RoadStep roadStep = (RoadStep) steps[i];
				RoadStep lastStep = (i > 0 && steps[i - 1] instanceof RoadStep) ? (RoadStep) steps[i]
						: null;
				if (lastStep != null) {
					TurnType type;

					if (lastStep.circle && roadStep.circle) {
						type = TurnType.stayOnCircle;
						Console.println("Stay on circle");
					} else if (lastStep.circle && !roadStep.circle) {
						type = TurnType.offCircle;
						Console.println("turning off circle");
					} else if (roadStep.circle) {
						type = TurnType.ontoCircle;
						Console.println("turning into circle");
					} else if (roadStep.direction == Direction.straight){
						type = TurnType.straight;
						Console.println("Going straight");
					} else {
						type = TurnType.normal;
						Console.println("Normal turn");
					}
					Console.println("Turning " + roadStep.direction.toString());
					turn(roadStep.direction, type);
				}

				Step nextStep = steps[i + 1];

				Direction dir = nextStep.direction == Direction.straight ? Direction.left
						: nextStep.direction;
				Console.println("Following " + dir);
				controller.followLine(dir, roadStep.slow, roadStep.circle);

			} else if (steps[i] instanceof GoalStep) {
				GoalStep goalStep = (GoalStep) steps[i];
				park(goalStep.space, goalStep.direction);
			}
		}
		turn(Direction.straight, TurnType.straight);
		// Park back in the entry point
		while(ColorFilter.red.evaluateAve(outerLeftColor.getColor()) < 0.5) {
			controller.follow(outerLeftColor, innerLeftColor, Direction.left, PARK_SPEED, false);
		}
		enterPark(outerLeftColor, Direction.left); // TODO: CHECK TO MAKE SURE THAT WILL TURN CORRECTLY
		pilot.stop();
		
		
	}

	public void turn(Direction dir, TurnType turnType) {
		float sign = dir == Direction.left ? 1 : -1;
		pilot.setTravelSpeed(TURN_SPEED);
		pilot.travel(turnType.getDist());
		pilot.rotate(turnType.getAngle() * sign);
		pilot.stop();
	}

	// ======================================================
	// Parking Logic
	// ======================================================

	public void driveToSpace(int space, Direction dir) {
		ColorHTSensor outerSensor = (dir == Direction.left) ? outerLeftColor
				: outerRightColor;
		ColorHTSensor innerSensor = (dir == Direction.left) ? innerLeftColor
				: innerRightColor;
		// drive until blue
		while (ColorFilter.blue.evaluateAve(innerSensor.getColor()) < 0.6) {
			controller.follow(innerSensor, null, dir, Robot.PARK_SPEED, false);
		}

		MembershipFunction searchColor = ColorFilter.black;
		MovingAverage filter = new MovingAverage(3);
		for (int i = 0; i < space * 2 - 1;) {
			controller.follow(innerSensor, null, dir, Robot.PARK_SPEED, false);
			filter.add(searchColor.evaluateAve(outerSensor.getColor()));
			if (filter.evaluate() > 0.6) {
				i++;
				searchColor = (searchColor == ColorFilter.white) ? ColorFilter.black
						: ColorFilter.white;
				filter.clear();
			}

		}
		leftMotor.setSpeed(0);
		rightMotor.setSpeed(0);
		leftMotor.stop();
		rightMotor.stop();
	}

	public void enterPark(ColorHTSensor sensor, Direction dir) {
		
		double tacho = aveTachoCount();
		while (aveTachoCount() - tacho < 250) {
			controller.follow(sensor, null, dir, Robot.PARK_SPEED, false);
		}
		pilot.setTravelSpeed(10);
		ColorHTSensor outerSensor;
		if (dir == Direction.left) {
			pilot.arcForward(2);
			outerSensor = outerLeftColor;
		} else {
			pilot.arcForward(-2);
			outerSensor = outerRightColor;
		}

		while (ColorFilter.white.evaluateAve(outerSensor.getColor()) < 0.5) {
		}

		pilot.stop();

		while (ColorFilter.white.evaluateAve(sensor.getColor()) < 0.5) {
			controller.follow(outerSensor, null, dir, Robot.PARK_SPEED, false);
		}

		pilot.stop();
	}

	public void exitPark(Direction dir) {
		int sign = dir == Direction.left ? -1 : 1;
		pilot.travel(-24);
		pilot.rotate(100 * sign);
	}

	public void park(int space, Direction direction) {		
		ColorHTSensor sensor = (direction == Direction.left) ? innerLeftColor
				: innerRightColor;
		
		driveToSpace(space, direction);
		enterPark(sensor, direction);
		exitPark(direction);
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
