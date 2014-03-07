package erhs53;

import erhs53.mapping.Map;
import erhs53.mapping.search.Action;

public class Miniurban2014 {
	
	public static void main(String[] args) {
		
		Action[] actions = new Action[31];
		actions[0] = new Action(0, Map.AH0);
		actions[1] = new Action(1, Map.AV0);
		actions[2] = new Action(1, Map.AH5);
		actions[3] = new Action(5, Map.G1);
		actions[4] = new Action(0, Map.AH5);
		actions[5] = new Action(3, Map.AH7);
		actions[6] = new Action(3, Map.AH8);
		actions[7] = new Action(3, Map.BH11);
		actions[8] = new Action(3, Map.BH12);
		actions[9] = new Action(3, Map.CH11);
		actions[10] = new Action(3, Map.CH12);
		actions[11] = new Action(1, Map.CV10);
		actions[12] = new Action(3, Map.CV2);
		actions[13] = new Action(5, Map.G9);
		actions[14] = new Action(0, Map.CV2);
		actions[15] = new Action(1, Map.CH1);
		actions[16] = new Action(3, Map.CH0);
		actions[17] = new Action(3, Map.BH1);
		actions[18] = new Action(3, Map.BH0);
		actions[19] = new Action(3, Map.AH1);
		actions[20] = new Action(1, Map.AV1);
		actions[21] = new Action(1, Map.AC1);
		actions[22] = new Action(2, Map.AH2);
		actions[23] = new Action(4, Map.G2);
		actions[24] = new Action(0, Map.AH2);
		actions[25] = new Action(3, Map.BH2);
		actions[26] = new Action(1, Map.BV0);
		actions[27] = new Action(1, Map.BH0);
		actions[28] = new Action(3, Map.AH1);
		actions[29] = new Action(3, Map.AH0);
		actions[30] = new Action(0, Map.END);

		Robot robot = new Robot();
	}	
}
