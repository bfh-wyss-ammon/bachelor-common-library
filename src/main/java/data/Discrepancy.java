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
 * This class is for serializing/parsing the results of the dispute resolving protocol to and from JSON. It is one entry in the list of dicrepancies of the dispute result.
 * It also stores to the database of the provider.
 */

package data;

import com.google.gson.annotations.Expose;

import interfaces.HashValue;

public class Discrepancy {

	private int resultId;
	private int disputeSessionId;

	@Expose
	@HashValue
	private int deltaToll;
	@Expose
	@HashValue
	private String userId;

	public Discrepancy() {

	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public int getDisputeSessionId() {
		return disputeSessionId;
	}

	public void setDisputeSessionId(int disputeSessionId) {
		this.disputeSessionId = disputeSessionId;
	}

	public int getDeltaToll() {
		return deltaToll;
	}

	public void setDeltaToll(int deltaToll) {
		this.deltaToll = deltaToll;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
