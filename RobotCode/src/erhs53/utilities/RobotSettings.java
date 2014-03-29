package erhs53.utilities;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import lejos.nxt.comm.RConsole;

public class RobotSettings {

	public static class Setting {
		public String name;
		public float value;

		public Setting(String name, float value) {
			this.name = name;
			this.value = value;
		}

		public String toString() {
			return name + "," + value + "\n";
		}
	}

	private static final LinkedList<Setting> settings;

	/**
	 * Read the settings
	 */
	static {
		RConsole.openBluetooth(0);
		settings = new LinkedList<>();
		File file = new File("settings");
		if (file.exists()) {
			try {
				InputStream is = new FileInputStream(file);
				DataInputStream din = new DataInputStream(is);

				StringBuffer strBuffer = new StringBuffer();

				while (is.available() > 3) { // at least 4 bytes left to read
					// Read a line from the file
					char ch;
					do {
						ch = (char) din.readByte();
						strBuffer.append(ch);
					} while (ch != '\n');
					String string = strBuffer.toString();
					string = string.substring(0, string.length() - 1); // remove the \n character					

					strBuffer.setLength(0);

					// Split the string
					String parts[] = StringUtils.splitString(string, ",");					
					float x = Float.parseFloat(parts[1]);
					settings.add(new Setting(parts[0], x));					
				}
				din.close();
			} catch (IOException e) {				
				System.exit(1);
			}
		}
	}

	public static float getSetting(String name) {
		for (Setting setting : settings) {
			if (setting.name.equals(name)) {
				return setting.value;
			}
		}
		return 0;
	}
	
	public static void setSetting(String name, float value) {
		for (Setting setting : settings) {
			if (setting.name.equals(name)) {
				setting.value = value;
				return;
			}
		}
		settings.add(new Setting(name, value));
	}

	public static void writeSettings() {
		FileOutputStream out = null; // declare outside the try block
		File file = new File("settings");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			out = new FileOutputStream(file);
		} catch (IOException e) {
			System.err.println("Failed to create output stream");
			System.exit(1);
		}

		DataOutputStream dataOut = new DataOutputStream(out);

		try { // write
			for (Setting setting : settings) {
				dataOut.write(setting.toString().getBytes());
			}
			out.close(); // flush the buffer and write the file
		} catch (IOException e) {
			System.err.println("Failed to write to output stream");
		}
	}
}