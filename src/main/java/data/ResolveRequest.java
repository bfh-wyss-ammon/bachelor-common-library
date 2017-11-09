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
