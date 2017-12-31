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
 * This class specifies the basic setting every spark API Router in our project has to implement.
 */

package rest;

import static spark.Spark.*;
import java.math.BigInteger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import data.CommonSettings;
import gson.BigIntegerTypeAdapter;
import util.Logger;
import util.RouterHelper;
import util.SettingsHelper;

public class BaseRouter {
	String tokenHeader = "x-secure-token";
	String token;

	public BaseRouter(int port, String token) {
		CommonSettings settings = SettingsHelper.getSettings(CommonSettings.class);
		if (settings.isTls()) {
			secure(settings.getKeyStoreFilePath(), settings.getKeyStorePassword(), settings.getTrustStoreFilePath(),
					settings.getTrustStorePassword());
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

		//handle debug Logging
		if (settings.isDebug()) {
			before("/*", (request, response) -> {
				String[] loglines = { "route in: " + request.requestMethod() + " " + request.url(),
						"body: " + request.body() };
				Logger.debugLogger(loglines);
			});

			after("/*", (request, response) -> {

				String[] loglines = { "route out: " + request.requestMethod() + " " + request.url(),
						"body: " + response.body() };
				Logger.debugLogger(loglines);
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
