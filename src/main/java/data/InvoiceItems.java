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
 * This class stores the list L that is sent from the provider to the mobile application in the toll calculation protocol. 
 * It serves serializing/parsing purpose of this data.
 * It is also used to get the HashValue on list L and store the provider signature on list L.
 */

package data;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.annotations.Expose;
import interfaces.HashValue;

public class InvoiceItems {

	@Expose
	@HashValue
	private Map<String, Integer> items;

	@Expose
	private String signature;

	@HashValue
	@Expose
	private String sessionId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public InvoiceItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Map<String, Integer> getItems() {
		if (items == null) {
			items = new HashMap<String, Integer>();
		}
		return items;
	}

	public void setItems(Map<String, Integer> items) {
		this.items = items;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}
