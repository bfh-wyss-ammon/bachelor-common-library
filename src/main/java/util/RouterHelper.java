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
 * This class enforces the methods and header settings to the API-Router.
 */

package util;

import spark.Request;
import spark.Response;

public class RouterHelper {
	public static String ConfigureOptions(Request request, Response response) {
		String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
		if (accessControlRequestHeaders != null) {
			response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
		}

		String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
		if (accessControlRequestMethod != null) {
			response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
		}
		return "OK";
	}

	public static void ConfigureBefore(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", Consts.Origin);
		response.header("Access-Control-Request-Method", Consts.Methods);
		response.header("Access-Control-Allow-Headers", Consts.Headers);

		response.type("application/json");
	}
	
}
