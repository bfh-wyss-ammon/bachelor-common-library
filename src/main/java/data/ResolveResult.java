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
 * This class stores the result of a dispute resolving session, that is sent from the authority to the provider.
 * It contains a signature from the authority and the list of discrepancies.
 */

package data;

import java.util.List;

import com.google.gson.annotations.Expose;

import interfaces.HashValue;

public class ResolveResult {

	@Expose
	@HashValue
	private List<Discrepancy> res;
	@Expose
	@HashValue
	private String disputeSessionId;
	@Expose
	private String authoritySignature;

	public List<Discrepancy> getRes() {
		return res;
	}

	public void setRes(List<Discrepancy> res) {
		this.res = res;
	}

	public String getDisputeSessionId() {
		return disputeSessionId;
	}

	public void setDisputeSessionId(String disputeSessionId) {
		this.disputeSessionId = disputeSessionId;
	}

	public String getAuthoritySignature() {
		return authoritySignature;
	}

	public void setAuthoritySignature(String authoritySignature) {
		this.authoritySignature = authoritySignature;
	}

}
