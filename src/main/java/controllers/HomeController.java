package controllers;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.context.request.SessionScope;
//import org.springframework.web.servlet.ModelAndView;

//import net.codejava.springmvc.Circle;
//import net.codejava.springmvc.Shape;
//import net.codejava.springmvc.dao.CustomerDAO;
//import net.codejava.springmvc.model.Customer;
import net.codejava.springmvc.dao.UserDAO;
import net.codejava.springmvc.model.User;
import net.codejava.springmvc.Auth;

 
/**
 * Handles requests for the application home page.
 */
@Controller
@Scope("session")
public class HomeController {
	
	//we can use static objects for transfer data betwen the classes
	public static List<String> userInformation = new ArrayList<String>(); 

	public List<String> user = new ArrayList<String>();
	
	public HomeController(){
		userInformation = user;
	}
	
	Auth Auth = new Auth();
	 
	
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
/*    @Scope("session")
    @RequestMapping(value = {"/", "/humans"}, method = RequestMethod.GET)
    public String home(Locale locale, Model model, HttpServletRequest request, HttpSession httpSession) {
        logger.info("Welcome home! The client locale is {}.", locale);
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.getRequest().getSession(true).getAttribute("Auth");
        
        try {
        	
            String Session = attr.getRequest().getSession().getAttribute("Auth").toString();
            
        	if(Auth.checkAuth(Session)){
        		
        		//System.out.println(user);
        		
            	//System.out.println(attr.getRequest().getSession(true).getAttribute("Auth"));

            	Date date = new Date();
                DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
                 
                String formattedDate = dateFormat.format(date);
                
                ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
           	 
                CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
                
                List<Customer> customerList = new ArrayList<Customer>();

                int limit = 15;
                customerList = customerDAO.findCustomers(limit, 0);
                
                
                int count = customerDAO.countCustomers();
                int pagination = (int)Math.ceil(count / limit);
                
                model.addAttribute("pagination", pagination);
                model.addAttribute("customerlist", customerList);
                model.addAttribute("page-title", "Home");
                model.addAttribute("serverTime", formattedDate );

                return "home";
        	} else {
        		return "auth";
        	}
        	
        } catch (Throwable t) {

        	System.out.println(t);
            return "auth";
            
        }
    	
    }*/
     
    @Scope("session")
	 @RequestMapping(value="/auth", method = RequestMethod.GET)
	    public String auth(Locale locale, Model model, HttpServletRequest request) {
	        logger.info("Welcome home! The client locale is {}.", locale);
	        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	       
	        attr.getRequest().getSession(true).getAttribute("Auth");
	        
	        try {
	        	
	        		String Session = attr.getRequest().getSession().getAttribute("Auth").toString();
	        			return "redirect:/dashboard";	
	        		
	        } catch (Throwable q) { 
		        
		        return "auth";
		        }
	    }
    
    @Scope("session")
    @RequestMapping(value="/login", method = RequestMethod.POST)
	public String register(Locale locale, Model model, HttpServletRequest request){
    	
    	logger.info("Welcome home! The client locale is {}.", locale);
    	ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        User userCheck = new User();
        
        attr.getRequest().getSession(true).getAttribute("Auth");
        
        try {
        	
        		String Session = attr.getRequest().getSession().getAttribute("Auth").toString();
        			return "redirect:/dashboard";	
        		
        } catch (Throwable q) {
        	
	        try {
	        	
		            ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		            UserDAO userDao = (UserDAO) context.getBean("userDAO");
		            String email = request.getParameter("email").toString();
		            String password = request.getParameter("password").toString();
		            
		            List<User> userList = new ArrayList<User>();
		            
		            userList = userDao.findUserByEmail(email);
		            userCheck = userList.get(0);
		            
		            
		            if(userCheck.getEmail().equals(email) && userCheck.getPassword().equals(password)){
			            request.getSession().setAttribute("Auth", "Authorized");
			            user.add(userCheck.getName().toString());
			            user.add(userCheck.getEmail().toString());
						return "redirect:/dashboard";
		            } else {
		            	return "redirect:/auth";
		            }
		            
	        } catch (Throwable t) {
	        	System.out.println(t);
				return "redirect:/auth";
	        }
	    	
        }
			
	}
    
    @Scope("session")
    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String dashboard(Locale locale, Model model, HttpServletRequest request, HttpSession httpSession) {
        logger.info("Welcome home! The client locale is {}.", locale);
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.getRequest().getSession(true).getAttribute("Auth");
        
        try {
        	
            String Session = attr.getRequest().getSession().getAttribute("Auth").toString();
            
        	if(Auth.checkAuth(Session)){
            	System.out.println(attr.getRequest().getSession(true).getAttribute("Auth"));

            	Date date = new Date();
                DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
                 
                String formattedDate = dateFormat.format(date);
                

                return "dashboard";
        	} else {
        		return "auth";
        	}
        	
        } catch (Throwable t) {

        	System.out.println(t);
            return "auth";
            
        }
    	
    }
    
}