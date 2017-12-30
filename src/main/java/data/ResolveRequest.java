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
 * This class stores all data that is sent from the provider to the authority in the dispute resolving protocol. That's why it is called a Resolve Request.
 * It is also used for serialization, parsing and hashing.
 */

package data;

import java.util.List;
import com.google.gson.annotations.Expose;
import interfaces.HashValue;

public class ResolveRequest {

	@Expose
	private int groupId;

	@Expose
	@HashValue
	private String disputeSessionId;

	@Expose
	@HashValue
	private List<ResolveTuple> S;
	@Expose
	private List<PaymentTuple> T;
	@Expose
	private String providerSignature;

	public ResolveRequest() {

	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getDisputeSessionId() {
		return disputeSessionId;
	}

	public void setDisputeSessionId(String disputeSessionId) {
		this.disputeSessionId = disputeSessionId;
	}

	public List<ResolveTuple> getS() {
		return S;
	}

	public void setS(List<ResolveTuple> s) {
		S = s;
	}

	public List<PaymentTuple> getT() {
		return T;
	}

	public void setT(List<PaymentTuple> t) {
		T = t;
	}

	public String getProviderSignature() {
		return providerSignature;
	}

	public void setProviderSignature(String providerSignature) {
		this.providerSignature = providerSignature;
	}

}
