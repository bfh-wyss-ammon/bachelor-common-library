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
 * This class has the static methods with the ORM Mapper related code. We use these methods to access the database.
 */

package util;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class DatabaseHelper {
	private static SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure(Paths.get("hibernate.cfg.xml").toFile()).buildSessionFactory();
		} catch (Exception ex) {
			// The registry would be destroyed by the SessionFactory, but we had trouble
			// building the SessionFactory
			// so destroy it manually.
			ex.printStackTrace();
			Logger.errorLogger(ex);

		}
	}

	public static <T> List<T> Get(Class<T> fieldType, String fieldName, Class parenType) {
		Session session = sessionFactory.openSession();
		List<T> result = (List<T>) session.createQuery("Select " + fieldName + " FROM " + getTableName(parenType))
				.list();
		session.close();
		return result;
	}

	public static <T> T Get(Class<T> t, int primaryKey) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		T result = (T) session.get(t, primaryKey);
		session.getTransaction().commit();
		session.close();
		return result;
	}

	private static String getTableName(Class t) {
		String name = t.getSimpleName();
		int i = name.lastIndexOf(".");
		return i > 0 ? name.substring(i) : name;
	}

	public static <T> T Get(Class<T> t, String where) {
		Session session = sessionFactory.openSession();
		T result = (T) session.createQuery("FROM " + getTableName(t) + " where " + where).getSingleResult();
		session.close();
		return result;
	}

	public static <T> List<T> GetList(Class<T> t, String where) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String query = "FROM " + getTableName(t) + " WHERE " + where;

		List<T> list = (List<T>) session.createQuery(query).list();
		session.close();
		return list;
	}

	public static <T> Boolean Exists(Class<?> t, String where) {
		Session session = sessionFactory.openSession();
		Long count = (Long) session.createQuery("SELECT count(*) FROM " + getTableName(t) + " where " + where)
				.uniqueResult();
		session.close();
		return count == 1;
	}

	public static <T> List<T> Get(Class<T> t) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<T> list = (List<T>) session.createQuery("FROM " + getTableName(t)).list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public static void Delete(Object entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
	}

	public static <T> void Delete(Serializable primaryKey, Class<T> t) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		T result = session.get(t, primaryKey);
		session.delete(result);
		session.getTransaction().commit();
		session.close();
	}

	public static <key> key Save(Class<?> key, Object obj) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Object res = session.save(obj);
		session.getTransaction().commit();
		session.close();
		return (key) res;
	}

	public static void Update(Object obj) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();
		session.close();
	}

	public static void SaveOrUpdate(Object obj) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(obj);
		session.getTransaction().commit();
		session.close();
	}

	public static void Delete(Class t, String dateField, Date olderThan) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("DELETE FROM " + getTableName(t) + " WHERE " + dateField + " < :date ");
		query.setParameter("date", olderThan).executeUpdate();
		session.getTransaction().commit();
		session.close();
		return;
	}

	public static void Delete(Class t, String where, String dateField, Date olderThan) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session
				.createQuery("DELETE FROM " + getTableName(t) + " WHERE " + dateField + " < :date AND " + where);
		query.setParameter("date", olderThan).executeUpdate();
		session.getTransaction().commit();
		session.close();
		return;
	}

	public static <T> List<T> Get(Class<T> t, String where, String dateField, Date after, Date before) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strAfter = format.format(after);
		String strBefore = format.format(before);

		String query = "FROM " + getTableName(t) + " WHERE " + where + " AND " + dateField + " >= '" + strAfter
				+ "' AND " + dateField + " <= '" + strBefore + "'";

		List<T> list = (List<T>) session.createQuery(query).list();
		session.close();
		return list;
	}

	public static <T> List<T> Get(Class<T> t, String where, String dateField, Date after, Date before,
			String dateField2, Date before2) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strAfter = format.format(after);
		String strBefore = format.format(before);
		String strBefore2 = format.format(before2);

		String query = "FROM " + getTableName(t) + " WHERE " + where + " AND " + dateField + " >= '" + strAfter
				+ "' AND " + dateField + " <= '" + strBefore + "' AND " + dateField2 + " <= '" + strBefore2 + "'";

		System.out.println(query);
		List<T> list = (List<T>) session.createQuery(query).list();
		session.close();
		return list;
	}

}
