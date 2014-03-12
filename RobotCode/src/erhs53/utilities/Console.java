package erhs53.utilities;

import lejos.nxt.comm.RConsole;

public class Console {
	
	public static void print(String s) {
		System.out.print(s);
		RConsole.print(s);
	}
	
	public static void println(String s) {
		System.out.println(s);
		RConsole.println(s);
	}

}
