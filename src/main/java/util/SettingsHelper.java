package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.Gson;


public class SettingsHelper {
	private static Gson gson;
	private static String filePath = "settings.json";

	static {
		gson = new Gson();
	}

	public static <T> T getSettings(Class<T> type) {
		FileReader reader;
		try {
			File f = new File(filePath);
			if(!f.exists()) {
				return type.newInstance();
			}
			reader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(reader);

			String line;
			String json = "";

			while ((line = bufferedReader.readLine()) != null) {
				json += line;
			}
			reader.close();
			return (T)gson.fromJson(json, type.getClass());

		} catch (Exception e) {
			// TODO ex handling
			e.printStackTrace();
		}
		return null;

	}

	public static <T> void saveSettings(Class<T> type, T settings) {
		String jsonSettings = gson.toJson((T)settings);

		FileWriter writer;
		try {
			writer = new FileWriter(filePath);
			writer.write(jsonSettings);
			writer.close();
		} catch (Exception e) {
			// TODO ex handling
			e.printStackTrace();
		}
	}
}
