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
 * This class stores the data of each element of the list S, which is part of the resolve request, which is sent from the provider to the authority in the dispute resolving protocol.
 */

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

	public void setSignature(BaseSignature signature) {
		this.signature = signature;
	}

}
