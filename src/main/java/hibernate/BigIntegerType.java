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
 * This class specifies how we have to adapt the google JSON parser to accommodate for BigInteger type.
 */

package hibernate;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;


public class BigIntegerType implements UserType {

	@Override
	public int[] sqlTypes() {
		return new int[] { StringType.INSTANCE.sqlType(), };
	}

	@Override
	public Class<BigIntegerType> returnedClass() {
		return BigIntegerType.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == null || y == null) {
			return false;
		}
		BigInteger a;
		BigInteger b;
		if (x.getClass() == String.class) {
			a = new BigInteger((String) x);
		} else {
			a = (BigInteger) x;
		}
		if (y.getClass() == String.class) {
			b = new BigInteger((String) y);
		} else {
			b = (BigInteger) y;
		}
		return a.equals(b);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
				
		String text = (String) StringType.INSTANCE.get(rs, names[0], session);
		if (text != null && text.length() > 0) {
			return new BigInteger(text);
		}
		return null;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		
		// TODO Auto-generated method stub
		if (value == null) {
			st.setNull(index, Types.OTHER);
		}
		else if(value.getClass() == BigInteger.class) {
			st.setString(index, ((BigInteger) value).toString());
		}
		else {
			st.setString(index, (String) value);
		}

	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		// TODO Auto-generated method stub
		if (value == null) {
			return null;
		}
		return ((BigInteger) value).toString();
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}
}