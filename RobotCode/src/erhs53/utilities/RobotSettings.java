package erhs53.utilities;

import lejos.nxt.Flash;

public class RobotSettings {

	public static final int MAX_SETTING_SIZE = 14;
	private static final int PAGE_ID = 200;	

	// Add to this String array to define new persistent settings.
	// There is a maximum of (256 / MAX_SETTING_SIZE) including the version.
	private static final String[] NAMES = { "white.r", "white.g", "white.b",
			"green.r", "green.g", "green.b", "yellow.r", "yellow.g", "yellow.b",
			"red.r", "red.g", "red.b", "blue.r", "blue.g", "blue.b",
			"black.r", "black.g", "black.b" };

	private static byte[] buf = new byte[256];

	/**
	 * Read the settings page
	 */
	static {
		Flash.readPage(buf, PAGE_ID);		
	}

	/**
	 * Get the slot number where a setting is stored
	 * 
	 * @param key
	 *            the setting name
	 * @return the slot number (0 - 15)
	 * 
	 */
	private static int getSlotIndex(String key) {
		for (int i = 0; i < NAMES.length; i++)
			if (NAMES[i].equals(key))
				return i;

		return -1;
	}

	/**
	 * Write the String value of a setting to a slot
	 * 
	 * @param slot
	 *            the slot (0 - 15)
	 * @param value
	 *            the String value
	 */
	private static void setSlotValue(int slot, String value) {
		int len = value.length();
		if (len > MAX_SETTING_SIZE)
			throw new IllegalArgumentException("value too large");

		for (int i = 0; i < len; i++)
			if (value.charAt(i) > 0xFF)
				throw new IllegalArgumentException("unsupported character");

		int off = slot * MAX_SETTING_SIZE;
		for (int i = 0; i < len; i++)
			buf[off + i] = (byte) value.charAt(i);

		for (int i = len; i < MAX_SETTING_SIZE; i++)
			buf[off + i] = 0;

		Flash.writePage(buf, PAGE_ID);
	}

	/**
	 * Get the String value from a slot
	 * 
	 * @param slot
	 *            the slot number
	 * @return the contents of the slot as a String
	 */
	private static String getSlotValue(int slot) {
		int off = slot * MAX_SETTING_SIZE;
		int len = 0;
		while (len < MAX_SETTING_SIZE && buf[off + len] != 0)
			len++;
		char[] chars = new char[len];
		for (int i = 0; i < len; i++)
			chars[i] = (char) (buf[off + i] & 0xFF);
		return new String(chars);
	}

	/**
	 * Get the value for a leJOS NXJ persistent setting as a String
	 * 
	 * @param key
	 *            the name of the setting
	 * @param defaultValue
	 *            the default value
	 * @return the value
	 */
	public static String getStringSetting(String key, String defaultValue) {
		int slot = getSlotIndex(key);
		if (slot < 0)
			return defaultValue;

		String s = getSlotValue(slot);
		if (s.length() == 0)
			return defaultValue;

		return s;
	}

	/**
	 * Get the value for a leJOS NXJ persistent setting as an Integer
	 * 
	 * @param key
	 *            the name of the setting
	 * @param defaultValue
	 *            the default value
	 * @return the value
	 */
	public static float getFloatSetting(String key, float defaultValue) {
		String s = getStringSetting(key, null);
		if (s == null)
			return defaultValue;
		try {
			return Float.parseFloat(s);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * Set a leJOS NXJ persistent setting.
	 * 
	 * @param key
	 *            the name of the setting
	 * @param value
	 *            the value to set it to
	 */
	public static void setSetting(String key, String value) {
		int slot = getSlotIndex(key);
		if (slot < 0)
			throw new IllegalArgumentException("unsupported key");

		setSlotValue(slot, value);
	}

	/**
	 * Get the names of the the leJOS NXJ Settings
	 * 
	 * @return a String array of the names
	 */
	static String[] getSettingNames() {
		return NAMES;
	}
}