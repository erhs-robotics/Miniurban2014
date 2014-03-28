package erhs53.utilities;

public class MovingAverage {
	private final float[] data;
	private int index = 0;
	private boolean initialized = false;
	
	public MovingAverage(int size) {		
		data = new float[size];	
	}
	
	public void clear() {
		initialized = false;
	}
	
	public void add(float x) {
		if(!initialized) {
			for(int i=0;i<data.length;i++) {
				data[i] = x;
			}
		} else {
			data[index++] = x;
			index = index % data.length;
		}
		initialized = true;
	}
	
	public float evaluate() {
		float sum = 0;
		for(float x : data) sum += x;
		return sum / data.length;		
	}
}
