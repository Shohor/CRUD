package de.shokhor.probation.Controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.shokhor.probation.Entity.User;
import de.shokhor.probation.Service.UserService;


@Controller
@SessionAttributes(value="users")
public class AppController {
	
	
private UserService userService;
	
	private List<User> users;
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService us){
		this.userService = us;
	}
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String listUser(Model model) {
		users = userService.findAllUsers();
		int size = users.size();
		if (size>10)
		{
			List<User> users1 = users.subList(0, 10);
			model.addAttribute("pageCount", size/10+1);
			model.addAttribute("user", new User());
			model.addAttribute("users", users1);
		}
		else
		{
			model.addAttribute("user", new User());
			model.addAttribute("users", users);
		}
		return "allusers";
	}
	
	//For add and update person both
		@RequestMapping(value= "/add", method = RequestMethod.POST)
		public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
			 if (result.hasErrors()) {
				 users =  this.userService.findAllUsers();
			    	if (users.size()>10)
			        {
			    		List<User> users1 = users.subList(0, 10);
			        	model.addAttribute("pageCount", users.size()/10+1);
						model.addAttribute("user", user);
						model.addAttribute("users", users1);
			        }
			    	else
			    	{
				        model.addAttribute("user", user);
				        model.addAttribute("users", users);
			    	}
				 return "allusers";
		    }
			if(user.getId() == null){
				//new person, add it
				user.setCreatedDate(new Date());
				this.userService.create(user);
			}else{
				//existing person, call update
				this.userService.update(user);
			}
			return "redirect:/";
		}
		
		@RequestMapping("/remove/{id}")
	    public String removeUser(@PathVariable("id") int id){
			
	        this.userService.delete(id);
	        return "redirect:/";
	    }
	 
	    @RequestMapping("/edit/{id}")
	    public String editUser(@PathVariable("id") int id, Model model){
	    	User user = this.userService.getUserById(id);
	    	users =  this.userService.findAllUsers();
	    	if (users.size()>10)
	        {
	    		List<User> users1 = users.subList(0, 10);
	        	model.addAttribute("pageCount", users.size()/10+1);
				model.addAttribute("user", user);
				model.addAttribute("users", users1);
	        }
	    	else
	    	{
		        model.addAttribute("user", user);
		        model.addAttribute("users", users);
	    	}
	        return "allusers";
	    }
	    
	    @RequestMapping(value="/find", method = RequestMethod.POST)
	    public String findByNameUser(@Valid @ModelAttribute("user") User user, Model model){
	        users = this.userService.findByName(user.getName());
	        if (users.size()>10)
	        {
	    		List<User> users1 = users.subList(0, 10);
	        	model.addAttribute("pageCount", users.size()/10+1);
				model.addAttribute("user", new User());
				model.addAttribute("users", users1);
	        }
	    	else
	    	{
		        model.addAttribute("user", new User());
		        model.addAttribute("users", users);
	    	}
	        return "allusers";
	    }
	    
	    @RequestMapping("/{id}")
	    public String pagingUser(@PathVariable("id") int id, Model model){
	        model.addAttribute("pageCount", users.size()/10+1);
	    	model.addAttribute("user", new User());
	        model.addAttribute("users", users.subList(id*10-10, users.size()));
	        return "allusers";
	    }
}
