/**
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
