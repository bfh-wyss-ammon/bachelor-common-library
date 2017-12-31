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
 * This class stores the data of one element of the list T in the dispute resolving protocol.
 * It also serves serializing / parsing and hashing of the same data.
 */

package data;

import com.google.gson.annotations.Expose;

import interfaces.HashValue;
import signatures.Signature;

public class PaymentTuple {

	@Expose
	@HashValue
	private int tollPaid;
	@Expose
	@HashValue
	private String[] tupleHashlist;
	@Expose
	@HashValue
	private String sessionId;
	@Expose
	private String providerSignature;
	@Expose
	@HashValue
	private BaseSignature userSignature;
	@Expose
	@HashValue
	private String hash;

	public PaymentTuple() {

	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getTollPaid() {
		return tollPaid;
	}

	public void setTollPaid(int tollPaid) {
		this.tollPaid = tollPaid;
	}

	public String[] getTupleHashlist() {
		return tupleHashlist;
	}

	public void setTupleHashlist(String[] tupleHashlist) {
		this.tupleHashlist = tupleHashlist;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getProviderSignature() {
		return providerSignature;
	}

	public void setProviderSignature(String providerSignature) {
		this.providerSignature = providerSignature;
	}

	public BaseSignature getUserSignature() {
		return userSignature;
	}

	public void setUserSignature(BaseSignature signature) {
		this.userSignature = signature;
	}

}
