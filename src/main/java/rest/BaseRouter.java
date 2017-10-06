package rest;

import static spark.Spark.before;
import static spark.Spark.options;
import static spark.Spark.port;

import java.math.BigInteger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gson.BigIntegerTypeAdapter;
import spark.Request;
import spark.Response;
import util.Consts;

public class BaseRouter {

	public BaseRouter(int port) {
		port(port);
		options("/*", (request, response) -> configureOptions(request, response));
		before((request, response) -> configureBefore(request, response));
	}

	private String configureOptions(Request request, Response response) {
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

	private void configureBefore(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", Consts.Origin);
		response.header("Access-Control-Request-Method", Consts.Methods);
		response.header("Access-Control-Allow-Headers", Consts.Headers);

		response.type("application/json");
	}

	public static Gson gson;
	static {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(BigInteger.class, new BigIntegerTypeAdapter());
		gson = builder.excludeFieldsWithoutExposeAnnotation().create();
	}
}
