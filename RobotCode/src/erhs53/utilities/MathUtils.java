package erhs53.utilities;

public class MathUtils {
	
	public static double clamp(double value, double min, double max) {
		if(value < min) value = min;
		else if(value > max) value = max;
		return value;
	}
	public static float clamp(float value, float min, float max) {
		if(value < min) value = min;
		else if(value > max) value = max;		
		return value;
	}
	
	public static boolean isWithin(int value, int min, int max) {
		if (value <=max && value >= min) return true;
		return false;
	}
	
	public static int arrayMax(int[] array) {
		int max = array[0];
		for(int i = 1; i < array.length; i++) {
			if (array[i] > max) max = array[i];
		}
		return max;
	}
	
	public static int arrayMin(int[] array) {
		int min = array[0];
		for(int i = 1; i < array.length; i++) {
			if (array[i] < min) min = array[i];
		}
		return min;
	}
	
	public static float mean(int[] data) {
		float sum = 0;
		for(int x : data) sum += x;
		return sum / data.length;
	}
	
	public static float map(float x, float in_min, float in_max, float out_min, float out_max) {
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
	
	public static int argMax(float... x) {
		int max_i = 0;
		
		for(int i=1;i<x.length;i++) {
			if(x[i] > x[max_i]) {
				max_i = i;			
			}
		}
		
		return max_i;
	}

	public static float max(float... x) {
		int max_i = 0;
		
		for(int i=1;i<x.length;i++) {
			if(x[i] > x[max_i]) {
				max_i = i;			
			}
		}
		
		return x[max_i];
	}

}
