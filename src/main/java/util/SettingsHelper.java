/**
 * This class helps to store the settings (AuthoritySettings, ProviderSettings, CommonSettings) to the file system in a JSON format.
 */

package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.Gson;


public class SettingsHelper {
	private static Gson gson;
	private static String filePath;

	static {
		gson = new Gson();
	}

	public static <T> T getSettings(Class<T> type) {
		filePath = type.getName()+ ".json";
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
			return (T)gson.fromJson(json, type);

		} catch (Exception e) {
			// TODO ex handling
			e.printStackTrace();
		}
		return null;

	}

	public static <T> void saveSettings(Class<T> type, T settings) {
		filePath = type.getName()+ ".json";
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
