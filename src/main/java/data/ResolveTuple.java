package data;

import com.google.gson.annotations.Expose;

import interfaces.HashValue;
import signatures.Signature;

public class ResolveTuple {

	@Expose
	@HashValue
	private String hash;
	@Expose
	@HashValue
	private int price;
	@Expose
	private BaseSignature signature;

		public ResolveTuple() {

		}

		public String getHash() {
			return hash;
		}

		public void setHash(String hash) {
			this.hash = hash;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public BaseSignature getSignature() {
			return signature;
		}

		public void setSignature(Signature signature) {
			this.signature = (BaseSignature) signature;
		}
		



}
