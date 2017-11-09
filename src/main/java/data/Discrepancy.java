package data;

import com.google.gson.annotations.Expose;

import interfaces.HashValue;

public class Discrepancy {

	@Expose
	@HashValue
	private int deltaToll;
	@Expose
	@HashValue
	private String userId;
	
	
	public Discrepancy() {
		
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
