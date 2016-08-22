package de.shokhor.probation.Service;

import java.util.Date;
import java.util.List;

import de.shokhor.probation.Entity.User;

public interface UserService {
	public void create(User user);
	
	public void update (User user);
	
	public void delete (int id);
	
	public User getUserById(int id);
	
	public List<User> findByName (String name);
	
	public List<User> findByCreatedDate (Date date);
	
	public List<User> findAllUsers();
}
