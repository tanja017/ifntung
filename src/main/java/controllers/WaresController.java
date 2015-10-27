package controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.context.request.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import jxl.write.WriteException;
//import net.codejava.springmvc.Circle;
//import net.codejava.springmvc.Shape;
import net.codejava.springmvc.dao.WarehousesDAO;
import net.codejava.springmvc.dao.WaresDAO;
import net.codejava.springmvc.model.Wares;
import net.codejava.springmvc.Auth;


import models.WriteExcel;
import models.FirstPdf;
import models.SimpleReportExample;

@Controller
@Scope("session")
public class WaresController {

	/*public WaresController(){
		wareInformation = ware;
	}*/
	
	Auth Auth = new Auth();
	 
	
    private static final Logger logger = LoggerFactory.getLogger(WaresController.class);
    
    @Scope("session")
    @RequestMapping(value = {"/wares"}, method = RequestMethod.GET)
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
           	 
                WaresDAO waresDAO = (WaresDAO) context.getBean("waresDAO");
                
                List<Wares> waresList = new ArrayList<Wares>();
                ///System.out.println(waresList);

                int limit = 15;
                waresList = waresDAO.findWares(limit, 0);
                
                
                //int count = waresDAO.countWares();
                int count = 5;
                int pagination = (int)Math.ceil(count / limit);
                
                model.addAttribute("pagination", pagination);
                model.addAttribute("wareslist", waresList);
                model.addAttribute("page-title", "Home");
                model.addAttribute("serverTime", formattedDate );

                return "wares";
        	} else {
        		return "auth";
        	}
        	
        } catch (Throwable t) {

        	//System.out.println(t);
            return "auth";
            
        }
    	
    }
    
    @RequestMapping("/edit_wares")
    public ModelAndView edit_customer(HttpServletRequest request) {
	 	int ID = Integer.parseInt(request.getParameter("wareID"));
	 	
	 	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        Map<String,String> ware = new HashMap<String,String>();
   	 
        WaresDAO waresDAO = (WaresDAO) context.getBean("waresDAO");
     
        ware = waresDAO.findCWareById(ID);
	 	
		ModelAndView model = new ModelAndView("edit_wares");
		model.addObject("userData", ware);
		/**
		 * 	<c:forEach items="${countries}" var="country">
	     * 	<option value="${country.key}">${country.value}</option>
	     *	</c:forEach>
		 */
		return model;
    }
    
    @Scope("session")
    @RequestMapping(value="/save_edit_ware", method = RequestMethod.POST)
	public String editCustomer(Locale locale, Model model, HttpServletRequest request){
    	
        try {
        	
        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        	WaresDAO waresDAO = (WaresDAO) context.getBean("waresDAO");
        	
            int ID = Integer.parseInt(request.getParameter("ID"));
        	String name = request.getParameter("name").toString();
            //int age = Integer.parseInt(request.getParameter("age"));
            
            Wares ware = new Wares(ID, name);
            waresDAO.update(ware);
        	
        } catch (Throwable q) {
        	
        }
        return "redirect:/wares";	
	}
    
    @Scope("session")
    @RequestMapping(value="/delete_ware", method = RequestMethod.GET)
	public String deleteCustomer(Locale locale, Model model, HttpServletRequest request){
    	
        try {
        	
        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        	WaresDAO waresDAO = (WaresDAO) context.getBean("waresDAO");
        	
            int ID = Integer.parseInt(request.getParameter("id"));
            //System.out.println(ID);
        	/*String name = request.getParameter("name").toString();
            int age = Integer.parseInt(request.getParameter("age"));
            
            Customer user = new Customer(ID, name, age);*/
            waresDAO.delete(ID);
        
        } catch (Throwable q) {
        	
        }
        return "redirect:/wares";	
	}
    
    @RequestMapping("/add_ware")
    public String add_ware( Model model, HttpServletRequest request) {
    	try{
    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    	WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
                 
        Map<Integer,HashMap<String,String>> ResultMap_WH = new HashMap<Integer, HashMap<String,String>>();

        ResultMap_WH = warehousesDAO.SQLquery("SELECT * FROM warehouse ");
        model.addAttribute("ResultMap_WH", ResultMap_WH);
		return "add_ware";
		
    }
    	catch (Throwable q) {
    		return "wares";
    	}
    }
    
    @Scope("session")
    @RequestMapping(value="/save_ware", method = RequestMethod.POST)
	public String save_ware(Locale locale, Model model, HttpServletRequest request){
    	
        try {
        	
        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        	WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
        	
        	String name = request.getParameter("name").toString();
            //System.out.println(name);
        	
            String insertSQL = "INSERT INTO ware " +
				" (name, type) VALUES ('" + name + "'," + 0 + " ) ";

            warehousesDAO.insert(insertSQL);
            System.out.println(insertSQL);
        	      	
        } catch (Throwable q) {
        	
        }
        return "redirect:/wares";	
	}
    
    @RequestMapping(value = "/printWaresList",headers = "Accept=application/json",  method = RequestMethod.POST,  produces="text/plain")
	@ResponseBody
    public String print_customerList(HttpServletRequest request) throws WriteException, IOException {
	 	String pdf_data = request.getParameter("pdf").toString();
	 	FirstPdf pdf = new FirstPdf();
		String link = pdf.writePdfFromHtml(pdf_data, "test2");
		
		return link;
    }
	
	@RequestMapping("/getWares")
    public ModelAndView getHumans(HttpServletRequest request) {
	 	
	 	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

		WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
		Map<Integer,HashMap<String,String>> ResultMap = new HashMap<Integer, HashMap<String,String>>();
        ResultMap = warehousesDAO.SQLquery("SELECT * FROM ware");
		
		ModelAndView model = new ModelAndView("list2pdf");
		model.addObject("ResultMap", ResultMap);
		return model;
    }
	
	@RequestMapping(value = "/printExcel",headers = "Accept=application/json",  method = RequestMethod.GET,  produces="text/plain")
	@ResponseBody
    public String writeExcel(HttpServletRequest request) throws WriteException, IOException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
		Map<Integer,HashMap<String,String>> ResultMap = new HashMap<Integer, HashMap<String,String>>();
        ResultMap = warehousesDAO.SQLquery("SELECT * FROM ware");
		
        WriteExcel excel = new WriteExcel(ResultMap);
        
		return "Ok";
    }
	
	
	@RequestMapping(value = "/printJasper",headers = "Accept=application/json",  method = RequestMethod.GET,  produces="text/plain")
	@ResponseBody
    public String writeJasper(HttpServletRequest request) throws WriteException, IOException {
		
        SimpleReportExample JReports = new SimpleReportExample();
		
		return "Ok";
    }
    
}
