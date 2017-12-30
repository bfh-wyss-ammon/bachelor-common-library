
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
 */

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import interfaces.HashValue;
import util.HashHelper;

public class Tests {

	class Demo {
		@HashValue
		private String animal;
		private String animal2;

		public String getAnimal() {
			return animal;
		}

		public void setAnimal(String animal) {
			this.animal = animal;
		}

		public String getAnimal2() {
			return animal2;
		}

		public void setAnimal2(String animal2) {
			this.animal2 = animal2;
		}

	}

	@Test
	public void testHash() {
		Demo d = new Demo();
		d.setAnimal("dog");
		d.setAnimal2("horse");
		// assertEquals("dog".getBytes(), "dog".getBytes());
		assertTrue(Arrays.equals("dog".getBytes(), HashHelper.getHash(d)));
	}
}
