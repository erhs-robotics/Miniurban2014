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
	
	public static double arrayAve(int[] array) {
		double sum = 0;
		for(int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum / (double)array.length;
	}
	
	public static double map(double x, double in_min, double in_max, double out_min, double out_max) {
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
		float max = 0;
		
		for(int i=1;i<x.length;i++) {
			if(x[i] > max) {
				max = i;			
			}
		}
		
		return max;
	}

}
