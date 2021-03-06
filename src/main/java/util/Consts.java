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
 * This class stores the constants to the API-Router directly in the code. These are not configurable by the application administrator.
 */

package util;

public class Consts {

	// for more: https://de.wikipedia.org/wiki/HTTP-Statuscode
	public static final int HttpStatuscodeUnauthorized = 401;
	public static final int HttpStatuscodeOk = 200;
	public static final int HttpInternalServerError = 500;
	public static final int HttpConflict = 409;
	public static final int HttpNotImplemented = 501;
	public static final int HttpBadRequest = 400;

	public static final String TokenHeader = "x-custom-token";
	public static final String ProviderTokenHeader = "x-custom-session-id";
	public static final String Origin = "*";
	public static final String Methods = "POST, GET, PUT";
	public static final String Headers = "Content-Type, x-custom-token, x-custom-session-id, x-secure-token";
	public static final String SignatureHeader = "x-custom-signature";

}
