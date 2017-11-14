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


	public void setUserSignature(Signature signature) {
		this.userSignature = (BaseSignature) signature;
	}
	
	

}
