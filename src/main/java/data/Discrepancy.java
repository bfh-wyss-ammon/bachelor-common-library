/**
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
