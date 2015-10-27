package controllers;

import java.text.DateFormat;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.springmvc.dao.CustomerDAO;
import net.codejava.springmvc.model.Customer;
import net.codejava.springmvc.model.User;

@Controller
public class UsersController {

	 
	 @RequestMapping("/list")
	    public ModelAndView home(HttpServletRequest request) {
		 	int page = Integer.parseInt(request.getParameter("page"));
		 	System.out.println(page);
		 	
		 	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
          	 
            CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
            
            List<Customer> customerList = new ArrayList<Customer>();

            int limit = 15;
            int offset = limit * page;
            
            customerList = customerDAO.findCustomers(limit, offset);
		 	
			ModelAndView model = new ModelAndView("list");
			model.addObject("customerList", customerList);

			/* List<String> e = new ArrayList<String>();
		      e.add("Reyan Ali");
		      try
		      {
			     File f = new File("Test.txt");
		         FileOutputStream fileOut = new FileOutputStream(f);
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(e);
		         out.close();
		         fileOut.close();
		         System.out.printf("Serialized data is saved in /tmp/employee.ser");
		         
		         FileInputStream fileIn = new FileInputStream(f);
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         e = (List<String>) in.readObject();
		         in.close();
		         fileIn.close();
		         
		         System.out.println(e);
		         
		      } catch (Throwable t) {
		    	  System.out.print(t);
		      }*/
			
			return model;
	    }
	 
	 @RequestMapping("/edit_customer")
	    public ModelAndView edit_customer(HttpServletRequest request) {
		 	int ID = Integer.parseInt(request.getParameter("userID"));
		 	
		 	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

	        Map<String,String> user = new HashMap<String,String>();
       	 
	        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
         
	        user = customerDAO.findCurrentCustomerById(ID);
		 	
			ModelAndView model = new ModelAndView("edit_customer");
			model.addObject("userData", user);
			/**
			 * 	<c:forEach items="${countries}" var="country">
		     * 	<option value="${country.key}">${country.value}</option>
		     *	</c:forEach>
			 */
			return model;
	    }
	 
	 @RequestMapping("/add_human")
	    public ModelAndView add_human(HttpServletRequest request) {
		 
			ModelAndView model = new ModelAndView("add_customer");
			return model;
			
	    }
	 
	 @Scope("session")
	    @RequestMapping(value="/save_edit_customer", method = RequestMethod.POST)
		public String editCustomer(Locale locale, Model model, HttpServletRequest request){
	    	
	        try {
	        	
	        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	        	CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
	        	
	            int ID = Integer.parseInt(request.getParameter("ID"));
	        	String name = request.getParameter("name").toString();
	            int age = Integer.parseInt(request.getParameter("age"));
	            
	            Customer user = new Customer(ID, name, age);
	            customerDAO.update(user);
	        	
	        } catch (Throwable q) {
	        	
	        }
	        return "redirect:/humans";	
		}
	 
	 @Scope("session")
	    @RequestMapping(value="/add_customer", method = RequestMethod.POST)
		public String addCustomer(Locale locale, Model model, HttpServletRequest request){
	    	
	        try {
	        	
	        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	        	CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
	        	
	            int ID = 0;
	        	String name = request.getParameter("name").toString();
	            int age = Integer.parseInt(request.getParameter("age"));
	            
	            Customer user = new Customer(ID, name, age);
	            customerDAO.insert(user);
	        	
	        } catch (Throwable q) {
	        	
	        }
	        return "redirect:/humans";	
		}
	 
	 @Scope("session")
	    @RequestMapping(value="/delete_customer", method = RequestMethod.GET)
		public String deleteCustomer(Locale locale, Model model, HttpServletRequest request){
	    	
	        try {
	        	
	        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	        	CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
	        	
	            int ID = Integer.parseInt(request.getParameter("id"));
	            System.out.println(ID);
	        	/*String name = request.getParameter("name").toString();
	            int age = Integer.parseInt(request.getParameter("age"));
	            
	            Customer user = new Customer(ID, name, age);*/
	            customerDAO.delete(ID);
	        
	        } catch (Throwable q) {
	        	
	        }
	        return "redirect:/humans";	
		}
	 
	/* @Scope("session")
	    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
	    public String dashboard(Locale locale, Model model, HttpServletRequest request, HttpSession httpSession) {
	        
	        try {
	        	
	            return "search";
	        } catch (Throwable t) {

	        	System.out.println(t);
	            return "search";
	            
	        }
	    	
	    }*/
	 
	 @Scope("session")
	    @RequestMapping(value="/search_list", method = RequestMethod.GET)
		public String searchList(Locale locale, Model model, HttpServletRequest request){
	    	
	        try {
	        	
	        	String sql = "SELECT * FROM CUSTOMER WHERE";
	        	
				if (request.getParameter("name").toString() != ""){
					sql += " NAME = " + request.getParameter("name").toString();
					sql += " AND";
				}
				if (request.getParameter("age") != ""){
					sql += " AGE = " + request.getParameter("age").toString();
					sql += " AND";
				}
	            
				sql += " CUST_ID > 0";
					
	            System.out.println(sql);
	        	
	        } catch (Throwable q) {

		        return "rtert";	
	        }
	        return "rtert";	
		}
	 
	 private List<String> getList() {

			List<String> list = new ArrayList<String>();
			list.add("List A");
			list.add("List B");
			list.add("List C");
			list.add("List D");
			list.add("List 1");
			list.add("List 2");
			list.add("List 3");

			return list;

		}
}
