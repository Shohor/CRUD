package de.shokhor.probation.DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import de.shokhor.probation.Entity.User;

@Repository
public class UserDaoImpl implements UserDAO 
{
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void create(User user) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.persist(user);
		session.flush();
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void update(User user) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);
		session.flush();
		session.getTransaction().commit();
		session.close();

	}
	@Override
	public void delete(int id) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		User user = (User)session.load(User.class, new Integer(id));
		session.delete(user);
		session.getTransaction().commit();
		session.close();
	}
	@Override
	public List<User> findByName(String name) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		List<User> list = (List<User>)session.createCriteria(User.class).add(Restrictions.like("name","%"+name+"%")).list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	@Override
	public List<User> findByCreatedDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllUsers() {	
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		List<User> list = session.createCriteria(User.class).list();
		session.flush();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public User getUserById(int id) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		User user = (User)session.createCriteria(User.class).add(Restrictions.eq("id", id)).uniqueResult();
		session.getTransaction().commit();
		session.close();
		return user;
		
		
	}

}
