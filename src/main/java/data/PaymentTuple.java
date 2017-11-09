package data;

public class PaymentTuple {
	
	private int tollPaid;
	private String[] tupleHashlist;
	private String sessionId;
	private String providerSignature;
	private BaseSignature userSignature;
	
	
	public PaymentTuple() {
		
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


	public void setUserSignature(BaseSignature userSignature) {
		this.userSignature = userSignature;
	}
	
	

}
