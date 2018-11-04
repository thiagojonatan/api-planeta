package br.com.b2w.starwars.util;

import java.util.ResourceBundle;

public class MessageUtil {

	private static ResourceBundle properties = null;

	static {

		properties = ResourceBundle.getBundle("message_validation");
	}

	public static String getMessage(String key) {
		try {
			return properties.getString(key);
		} catch (Exception e) {
			System.err.print(e.getMessage());
		}
		return null;
	}

}
