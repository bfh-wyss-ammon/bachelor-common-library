package util;

import java.io.Serializable;
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
	private static final StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	static {
		registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble
			// building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}
	}

	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();

		} catch (Exception e) {
			e.printStackTrace();
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
		Query query = session.createQuery("DELETE FROM " + getTableName(t) + " WHERE " + dateField +" < :date ");
		query.setParameter("date", olderThan).executeUpdate();
		session.getTransaction().commit();
		session.close();
		return;
	}
	
	public static  <T> List<T> Get(Class<T> t, String where, String dateField, Date after, Date before) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<T> list = (List<T>)session.createQuery("FROM " + getTableName(t) + " WHERE " + where + " AND " + dateField +" BETWEEN " + after + " AND " + before);
		session.getTransaction().commit();
		session.close();
		return list;
	}
	

}
