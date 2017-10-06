package data;

import java.math.BigDecimal;
import java.util.Date;

import interfaces.HashValue;
import signatures.Signature;

public class Tuple {
	private int groupId;
	@HashValue
	private BigDecimal longitude;
	@HashValue
	private BigDecimal latitude;
	@HashValue
	private Date created;
	private BaseSignature signature;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public BaseSignature getSignature() {
		return signature;
	}

	public void setSignature(BaseSignature signature) {
		this.signature = signature;
	}

}
