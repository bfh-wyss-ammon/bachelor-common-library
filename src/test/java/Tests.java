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
		//assertEquals("dog".getBytes(), "dog".getBytes());
		assertTrue(Arrays.equals("dog".getBytes(), HashHelper.getHash(d)));
	}
}
