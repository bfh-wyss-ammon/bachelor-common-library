package rest;

import static spark.Spark.*;
import java.math.BigInteger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gson.BigIntegerTypeAdapter;
import util.RouterHelper;

public class BaseRouter {
	String tokenHeader = "x-secure-token";
	String token;
	
	public BaseRouter(int port, String token) {
		port(port);
		this.token = token;
		
		this.RegisterWebSockets();
		this.RegisterRoutes();

		options("/*", (request, response) -> RouterHelper.ConfigureOptions(request, response));
		before((request, response) -> RouterHelper.ConfigureBefore(request, response));

	}

	public void WebSockets() {

	}

	private final void RegisterWebSockets() {
		path("/sockets", () -> {
			this.WebSockets();
		});
	}

	public void Routes() {

	}

	public void ProtectedRoutes() {

	}

	private final void RegisterRoutes() {

		before("/api/protected/*", (request, response) -> {
			String cToken = request.headers(tokenHeader);
			
			if(request.requestMethod() == "OPTIONS") {
				return;
			}
						
			if (cToken == null || !cToken.equals(token)) {
				halt(401, "Go Away!");
			}
		});

		before("/sockets/*", (request, response) -> {
			
			String cToken = request.queryParams("token");
			System.out.println(request.requestMethod() + "token" + cToken);
			if (cToken == null || !cToken.equals(token)) {
				halt(401, "Go Away!");
			}
		});

		path("/api", () -> {
			this.Routes();
		});

		path("/api/protected", () -> {
			this.ProtectedRoutes();
		});
	}

	public static Gson gson;
	static {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(BigInteger.class, new BigIntegerTypeAdapter());
		gson = builder.excludeFieldsWithoutExposeAnnotation().create();
	}
}
