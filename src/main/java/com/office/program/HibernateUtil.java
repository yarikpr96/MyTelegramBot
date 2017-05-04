package com.office.program;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
}
