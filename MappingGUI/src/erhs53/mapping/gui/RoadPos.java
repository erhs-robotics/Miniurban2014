package erhs53.mapping.gui; 

import java.awt.Point;
import java.util.HashMap;

import erhs53.mapping.Road;

public class RoadPos {

	static int HORIZ = 0;
	static int VERT = 1;
	
	public Point startPos, endPos;
	public boolean circle = false;
	
	public RoadPos(Point startPos, Point endPos) {
		this.startPos = startPos;
		this.endPos = endPos;
	}
	
	public RoadPos(Point startPos, Point endPos, boolean circle) {
		this(startPos, endPos);
		this.circle = circle;
	}
	
	public RoadPos() {}
	
	public static void get(Road r) {
		
	}
	
	public static RoadPos AH0 = new RoadPos(new Point(35, 20), new Point(242, 20)),
					      AH1 = new RoadPos(new Point(242, 20), new Point(500, 20)),
					      AH2 = new RoadPos(new Point(320, 65), new Point(500, 65)),
					      AH3 = new RoadPos(new Point(395, 155), new Point(500, 155)),
					      AH4 = new RoadPos(new Point(242, 200), new Point(500, 230)),
					      AH5 = new RoadPos(new Point(35, 300), new Point(180, 300)),
					      AH7 = new RoadPos(new Point(180, 300), new Point(300, 300)),
					      AH8 = new RoadPos(new Point(300, 300), new Point(607, 300)),
					      AV0 = new RoadPos(new Point(35, 20), new Point(35, 300)),
					      AV1 = new RoadPos(new Point(242, 20), new Point(242, 120)),
					      AV2 = new RoadPos(new Point(242, 120), new Point(242, 200)),
					      AV3 = new RoadPos(new Point(242, 200), new Point(242, 255)),
					      AV4 = new RoadPos(new Point(395, 155), new Point(395, 230)),
					      AV5 = new RoadPos(new Point(500, 20), new Point(500, 65)),
					      AV6 = new RoadPos(new Point(500, 65), new Point(500, 155)),
					      AV7 = new RoadPos(new Point(500, 155), new Point(500, 230)),
					      AV8 = new RoadPos(new Point(500, 230), new Point(500, 300)),
			              AC0 = new RoadPos(new Point(250, 65), new Point(320, 65), true),
			              AC1 = new RoadPos(new Point(250, 120), new Point(320, 65), true),
			              AC2 = new RoadPos(new Point(180, 300), new Point(242, 255), true),
			             AC3B = new RoadPos(new Point(242, 255), new Point(300, 300), true),
			
			              BH0 = new RoadPos(new Point(500, 20), new Point(715, 20)),
			              BH1 = new RoadPos(new Point(715, 20), new Point(863, 20)),
	                      BH2 = new RoadPos(new Point(500, 65), new Point(607, 65)),
	                      BH3 = new RoadPos(new Point(607, 65), new Point(715, 65)),
	                      BH4 = new RoadPos(new Point(715, 65), new Point(863, 65)),
	                      BH5 = new RoadPos(new Point(500, 155), new Point(607, 155)),
	                      BH6 = new RoadPos(new Point(607, 155), new Point(673, 155)),
	                      BH7 = new RoadPos(new Point(757, 155), new Point(863, 155)),
	                      BH8 = new RoadPos(new Point(500, 230), new Point(607, 230)),
	                      BH9 = new RoadPos(new Point(607, 230), new Point(715, 230)),
	                     BH10 = new RoadPos(new Point(715, 230), new Point(863, 230)),
	                     BH11 = new RoadPos(new Point(607, 300), new Point(790, 300)),
	                     BH12 = new RoadPos(new Point(790, 300), new Point(955, 300)),
	                      BV0 = new RoadPos(new Point(607, 20), new Point(607, 65)),
	                      BV1 = new RoadPos(new Point(715, 20), new Point(715, 65)),
	                      BV2 = new RoadPos(new Point(785, 20), new Point(785, 65)),
	                      BV3 = new RoadPos(new Point(863, 20), new Point(863, 65)),
	                      BV4 = new RoadPos(new Point(607, 65), new Point(607, 155)),
	                      BV5 = new RoadPos(new Point(715, 65), new Point(715, 115)),
	                      BV6 = new RoadPos(new Point(863, 65), new Point(863, 155)),
	                      BV7 = new RoadPos(new Point(607, 155), new Point(607, 230)),
	                      BV8 = new RoadPos(new Point(715, 200), new Point(715, 230)),
	                      BV9 = new RoadPos(new Point(863, 155), new Point(863, 230)),
	                     BV10 = new RoadPos(new Point(607, 230), new Point(607, 300)),
	                     BV11 = new RoadPos(new Point(715, 230), new Point(715, 300)),
	                     BV12 = new RoadPos(new Point(785, 230), new Point(785, 300)),
	                     BV13 = new RoadPos(new Point(863, 230), new Point(863, 300)),
	                      BC0 = new RoadPos(new Point(715, 200), new Point(673, 155), true),
	                      BC1 = new RoadPos(new Point(673, 155), new Point(715, 200), true),
	
	                      CH0 = new RoadPos(new Point(715, 20), new Point(1024, 20)),
	                      CH1 = new RoadPos(new Point(1024, 20), new Point(1228, 20)),
	                      CH2 = new RoadPos(new Point(863, 65), new Point(1024, 65)),
	                      CH3 = new RoadPos(new Point(1024, 65), new Point(1228, 65)),
	                      CH4 = new RoadPos(new Point(863, 155), new Point(955, 155)),
	                      CH6 = new RoadPos(new Point(863, 230), new Point(955, 230)),
	                      CH7 = new RoadPos(new Point(955, 230), new Point(1024, 230)),
	                      CH8 = new RoadPos(new Point(1024, 230), new Point(1135, 230)),
	                      CH9 = new RoadPos(new Point(1135, 230), new Point(1228, 230)),
	                     CH11 = new RoadPos(new Point(955, 300), new Point(1135, 300)),
	                     CH12 = new RoadPos(new Point(1135, 300), new Point(1128, 300)),
	                      CV0 = new RoadPos(new Point(1024, 20), new Point(1024, 65)),
	                      CV1 = new RoadPos(new Point(1135, 20), new Point(1135, 65)),
	                      CV2 = new RoadPos(new Point(1228, 20), new Point(1228, 230)),
	                      CV3 = new RoadPos(new Point(1024, 65), new Point(1024, 230)),
	                      CV4 = new RoadPos(new Point(1135, 65), new Point(1135, 230)),
	                      CV5 = new RoadPos(new Point(955, 155), new Point(955, 230)),
	                      CV7 = new RoadPos(new Point(955, 230), new Point(955, 300)),
	                      CV8 = new RoadPos(new Point(1024, 230), new Point(1024, 300)),
	                      CV9 = new RoadPos(new Point(1135, 230), new Point(1135, 300)),
	                     CV10 = new RoadPos(new Point(1228, 230), new Point(1228, 300));
}