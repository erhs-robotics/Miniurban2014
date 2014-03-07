package erhs53.control;

import lejos.robotics.Color;
import erhs53.utilities.MathUtils;

public class MembershipFunction {
	
	// ======================================================
	// Nested Classes
	// ======================================================
	
	public static class ComponentFunction {
		float color1; // The mean of the color expected at full membership
		float color0; // The mean of the color expected at 0 membership
		
		public ComponentFunction(float color1, float color0) {
			this.color1 = color1;
			this.color0 = color0;
		}
		
		public float evaluate(float x) {
			float value = 1 / (color1 - color0) * (x - color0);
			return MathUtils.clamp(value, 0, 1);
		}
	}
	
	// ======================================================
	// Variables
	// ======================================================
	
	private ComponentFunction[] componentFunctions;
	
	// ======================================================
	// Constructor
	// ======================================================
	
	public MembershipFunction(ComponentFunction... componentFunctions) {
		this.componentFunctions = componentFunctions;
	}
	
	// ======================================================
	// Class Logic
	// ======================================================
	
	private float[] getComponents(Color color) {
		float[] result = new float[3];
		result[0] = componentFunctions[0].evaluate(color.getRed());
		result[1] = componentFunctions[1].evaluate(color.getGreen());
		result[2] = componentFunctions[2].evaluate(color.getBlue());
		return result;
	}
	
	public float evaluateAve(Color color) {
		float[] components = getComponents(color);
		return (components[0] + components[1] + components[2]) / 3;
	}
}
