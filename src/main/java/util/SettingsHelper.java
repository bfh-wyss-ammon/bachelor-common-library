/**
 *   Copyright 2018 Pascal Ammon, Gabriel Wyss
 * 
 * 	 Implementation eines anonymen Mobility Pricing Systems auf Basis eines Gruppensignaturschemas
 * 
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
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

		} catch (Exception ex) {
			// TODO ex handling
			ex.printStackTrace();
			Logger.errorLogger(ex);

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
		} catch (Exception ex) {
			// TODO ex handling
			ex.printStackTrace();
			Logger.errorLogger(ex);

		}
	}
}
