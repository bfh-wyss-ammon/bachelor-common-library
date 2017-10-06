package util;

import java.lang.reflect.Field;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import interfaces.HashValue;

public class HashHelper {
	private static final DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
	
	// this methods generate the hash value of an object based ond the @HashValue Annotation
	// check the test case for more info
	public static byte[] getHash(Object value) {
		StringBuilder builder = new StringBuilder();
		if(value != null) {
			for(Field field : value.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if(field.isAnnotationPresent(HashValue.class))
				{
					try {
						if(field.get(value).getClass() == Date.class) {
							builder.append(dateFormat.format((Date)field.get(value)));
						}
						else {
							builder.append(field.get(value).toString());							
						}
					} 
					catch (Exception e) {
						// todo error handling!
						e.printStackTrace();
					}
				}
			}
			
		}
		return builder.toString().getBytes();
	}
}
