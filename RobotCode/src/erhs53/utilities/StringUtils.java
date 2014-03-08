package erhs53.utilities;

import java.util.LinkedList;

public final class StringUtils {
	public static final String[] splitString(String str, String key) {		
		LinkedList<String> parts = new LinkedList<>();
		int start = 0;
		int end = str.indexOf(key);
		
		while(end != -1) {
			if(start != end)
				parts.add(str.substring(start, end));
			
			start = end + key.length();
			end = str.indexOf(key, start);
			//System.out.println(end);
		}
		if(start != str.length())
		parts.add(str.substring(start, str.length()));
		
		return parts.toArray(new String[parts.size()]);
	}
}
