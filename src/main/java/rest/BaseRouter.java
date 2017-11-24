package rest;

import static spark.Spark.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import data.CommonSettings;
import gson.BigIntegerTypeAdapter;
import util.RouterHelper;
import util.SettingsHelper;

public class BaseRouter {
	String tokenHeader = "x-secure-token";
	String token;

	public BaseRouter(int port, String token) {
		CommonSettings settings = SettingsHelper.getSettings(CommonSettings.class);
		if(settings.isTls()) {
			secure(settings.getKeyStoreFilePath(), settings.getKeyStorePassword(), settings.getTrustStoreFilePath(), settings.getTrustStorePassword());			
		}
		
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

		CommonSettings settings = SettingsHelper.getSettings(CommonSettings.class);

		if (settings.isDebug()) {
			before("/*", (request, response) -> {
				try{
					Path file = Paths.get("debugLog.txt");
					if(!Files.exists(file)) {
						Files.createFile(file);
					}
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat();
				List<String> lines = Arrays.asList(format.format(date) + " route in: " + request.requestMethod() + " " + request.url(),
						"body: " + request.body());

				Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			after("/*", (request, response) -> {
				try {
					Path file = Paths.get("debugLog.txt");
					if(!Files.exists(file)) {
						Files.createFile(file);
					}
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat();
				List<String> lines = Arrays.asList(format.format(date) + " route out: " + request.requestMethod() + " " + request.url(),
						"body: " + response.body());
				Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}

		before("/api/protected/*", (request, response) -> {
			String cToken = request.headers(tokenHeader);

			if (request.requestMethod() == "OPTIONS") {
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
