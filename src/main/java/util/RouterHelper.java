/**
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
