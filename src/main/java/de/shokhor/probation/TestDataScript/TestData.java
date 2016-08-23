package de.shokhor.probation.TestDataScript;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class TestData {
	
	public static void main (String[] args)
	{
		
		ApplicationContext context = new ClassPathXmlApplicationContext("DBcontext.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("mysessionFactory");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.createSQLQuery("insert into user (name, age, isAdmin, createdDate) values ('Ivanov ivan', 23, true, Now()), ('Sidorov Fedor', 45, false, Now())").executeUpdate();
		session.createSQLQuery("insert into user (name, age, isAdmin, createdDate) values ('Jon Mikle', 18, true, Now()), ('Petrova Katya', 37, false, Now())").executeUpdate();
		session.createSQLQuery("insert into user (name, age, isAdmin, createdDate) values ('Иванов Иван', 21, true, Now()), ('Прусова Мария', 37, false, Now())").executeUpdate();
		session.createSQLQuery("insert into user (name, age, isAdmin, createdDate) values ('Fedorov Igor', 57, false, Now()), ('Ivanov ivan', 45, false, Now())").executeUpdate();
		session.createSQLQuery("insert into user (name, age, isAdmin, createdDate) values ('Антонов Антон', 49, false, Now()), ('Рашидов Артем', 51, true, Now())").executeUpdate();
		session.createSQLQuery("insert into user (name, age, isAdmin, createdDate) values ('Евграфова Екатерина', 37, false, Now()), ('Алексеева Татьяна', 25, false, Now())").executeUpdate();
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
}
