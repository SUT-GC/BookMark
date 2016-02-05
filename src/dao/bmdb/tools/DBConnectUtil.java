package dao.bmdb.tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBConnectUtil {
	public static final SessionFactory sessionFactory;
	static {
		try {
			Configuration cfg = new Configuration().configure("bmdb.cfg.xml");
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).build();
			sessionFactory = cfg.buildSessionFactory(serviceRegistry);
			System.out.println("SessionFactory创建成功");
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session currentSession() {
		Session session = sessionFactory.openSession();
		return session;
	}

	public static void closeSession(Session session) {
		session.close();
	}
}
