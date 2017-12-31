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
 * This class handles the logging features.
 */

package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Logger {

	public static void debugLogger(String[] lines) {
		logger(lines, Paths.get("debugLog.txt"));

	}

	public static void errorLogger(String[] lines) {
		logger(lines, Paths.get("errorLog.txt"));
	}

	public static void debugLogger(List<String> lines) {
		logger((String[]) lines.toArray(), Paths.get("debugLog.txt"));

	}

	public static void errorLogger(List<String> lines) {
		logger((String[]) lines.toArray(), Paths.get("errorLog.txt"));
	}
	
	public static void errorLogger(Throwable exception) {
		String [] lines = {stackTraceToString(exception)};
		logger(lines, Paths.get("errorLog.txt"));
	}

	private static void logger(String[] lines, Path file) {

		try {
			if (!Files.exists(file)) {
				Files.createFile(file);
			}
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat();
			List<String> line = new ArrayList<String>();
			
			for(int i = 0; i < lines.length; i++) {
				line.add(lines[i]);
			}
			
			line.add(0, format.format(date));
			Files.write(file, line, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String stackTraceToString(Throwable exception) {
		if (exception == null) {
			return "";
		}
		StringWriter string = new StringWriter();
		PrintWriter writer = new PrintWriter(string);
		try {
			exception.printStackTrace(writer);
			return string.getBuffer().toString();
		} finally {
			try {
				string.close();
				writer.close();
			} catch (IOException e) {
				// todo
			}
		}
	}

}
