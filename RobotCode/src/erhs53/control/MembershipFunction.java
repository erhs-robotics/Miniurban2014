package erhs53.control;

import lejos.robotics.Color;
import erhs53.utilities.MathUtils;

public class MembershipFunction {
	// Component Membership Function
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
	
	private ComponentFunction[] componentFunctions;
	
	public MembershipFunction(ComponentFunction... componentFunctions) {
		this.componentFunctions = componentFunctions;
	}
	
	private float[] getComponents(Color color) {
		float[] result = new float[3];
		result[0] = componentFunctions[0].evaluate(color.getRed());
		result[1] = componentFunctions[1].evaluate(color.getGreen());
		result[2] = componentFunctions[2].evaluate(color.getBlue());
		return result;
	}
	
	public float evaluate(Color color) {
		float[] components = getComponents(color);
		return components[0] * components[1] * components[2];
	}
	
	public float evaluateMagnitude(Color color) {
		float[] components = getComponents(color);
		double sumSquares = Math.pow(components[0], 2) + Math.pow(components[1], 2) + Math.pow(components[2], 2);
		return  (float) (1 / Math.sqrt(3) * Math.sqrt(sumSquares));
	}
	
	public float evaluateAve(Color color) {
		float[] components = getComponents(color);
		return (components[0] + components[1] + components[2]) / 3;
	}
}
