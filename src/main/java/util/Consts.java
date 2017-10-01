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
	public static final String Origin = "*";
	public static final String Methods = "POST, GET, PUT";
	public static final String Headers = "Content-Type, x-custom-token";

}
