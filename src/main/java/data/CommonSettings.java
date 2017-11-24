package data;

import static spark.Spark.secure;

public class CommonSettings {

	private boolean debug;
	private boolean tls;
	private String keyStoreFilePath;
	private String keyStorePassword;
	private String trustStoreFilePath;
	private String trustStorePassword;

	public CommonSettings() {

		// constants
		this.debug = true;
		this.tls = false;
		this.keyStoreFilePath = "keystore.jks";
		this.trustStoreFilePath = "truststore.jks";

		// secret configurables
		this.keyStorePassword = "chose good password";
		this.trustStorePassword = "chose good password";

	}

	public String getKeyStoreFilePath() {
		return keyStoreFilePath;
	}

	public void setKeyStoreFilePath(String keyStoreFilePath) {
		this.keyStoreFilePath = keyStoreFilePath;
	}

	public String getKeyStorePassword() {
		return keyStorePassword;
	}

	public void setKeyStorePassword(String keyStorePassword) {
		this.keyStorePassword = keyStorePassword;
	}

	public String getTrustStoreFilePath() {
		return trustStoreFilePath;
	}

	public void setTrustStoreFilePath(String trustStoreFilePath) {
		this.trustStoreFilePath = trustStoreFilePath;
	}

	public String getTrustStorePassword() {
		return trustStorePassword;
	}

	public void setTrustStorePassword(String trustStorePassword) {
		this.trustStorePassword = trustStorePassword;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isTls() {
		return tls;
	}

	public void setTls(boolean tls) {
		this.tls = tls;
	}

}
