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
 * This class stores the settings that are common to the provider and the authority in a plain text file on the file system.
 */

package data;

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
