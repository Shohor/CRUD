package de.shokhor.probation.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.shokhor.probation.DAO.UserDAO;
import de.shokhor.probation.Entity.User;

@Service
public class UserServiceImpl implements UserService
{
	private UserDAO userDAO;
	
	public UserServiceImpl()
	{
		
	}
	public UserServiceImpl(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public void create(User user) {
		this.userDAO.create(user);
		
	}

	@Override
	@Transactional
	public void update(User user) {
		this.userDAO.update(user);
		
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.userDAO.delete(id);
		
	}

	@Override
	@Transactional
	public List<User> findByName(String name) {
		// TODO Auto-generated method stub
		return this.userDAO.findByName(name);
	}

	@Override
	@Transactional
	public List<User> findByCreatedDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<User> findAllUsers() {
		return this.userDAO.findAllUsers();
	}
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return this.userDAO.getUserById(id);
	}

}
