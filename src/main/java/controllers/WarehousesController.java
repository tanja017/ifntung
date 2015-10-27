package controllers;

import java.text.DateFormat;
//import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
//import java.util.List;
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
//import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.context.request.SessionScope;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.ModelAndView;

import net.codejava.springmvc.dao.WarehousesDAO;
//import net.codejava.springmvc.model.Warehouses;
import net.codejava.springmvc.Auth;

@Controller
@Scope("session")
public class WarehousesController {

	/*public WaresController(){
		wareInformation = ware;
	}*/
	
	Auth Auth = new Auth();
	 
	
    private static final Logger logger = LoggerFactory.getLogger(WarehousesController.class);
    
    @Scope("session")
    @RequestMapping(value = {"/warehouses"}, method = RequestMethod.GET)
    public String warehouses(Locale locale, Model model, HttpServletRequest request, HttpSession httpSession) {
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
           	 
                WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
                
                int limit = 15;
                
                Map<Integer,HashMap<String,String>> ResultMap = new HashMap<Integer, HashMap<String,String>>();
                ResultMap = warehousesDAO.SQLquery("SELECT * FROM warehouse LIMIT "+limit+" OFFSET 0");
               
                //int count = waresDAO.countWares();
                int count = 5;
                int pagination = (int)Math.ceil(count / limit);
                
                model.addAttribute("pagination", pagination);
                //model.addAttribute("whouseslist", warehousesList);
                model.addAttribute("ResultMap", ResultMap);
                model.addAttribute("page-title", "Home");
                model.addAttribute("serverTime", formattedDate );

                return "warehouses";
        	} else {
        		return "auth";
        	}
        	
        } catch (Throwable t) {

        	//System.out.println(t);
            return "auth";
            
        }
    	
    }
    
    @RequestMapping("/edit_warehouse")
    public String edit_warehouse(HttpServletRequest request, Model model ) {
	 	int ID = Integer.parseInt(request.getParameter("whouseid"));
	 	
	 	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        //Map<String,String> whouse = new HashMap<String,String>();
   	 
        WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
            
        Map<Integer,HashMap<String,String>> ResultMap = new HashMap<Integer, HashMap<String,String>>();
        ResultMap = warehousesDAO.SQLquery("SELECT * FROM WAREHOUSE WHERE ID = " +ID);
        
        model.addAttribute("ResultMap", ResultMap.get(0));
		/**
		 * 	<c:forEach items="${countries}" var="country">
	     * 	<option value="${country.key}">${country.value}</option>
	     *	</c:forEach>
		 */
		return "edit_warehouse";
    }
    
    @Scope("session")
    @RequestMapping(value="/save_edit_warehouse", method = RequestMethod.POST)
	public String save_edit_warehouse(Locale locale, Model model, HttpServletRequest request){
    	
        try {
        	
        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        	WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
        	
            int ID = Integer.parseInt(request.getParameter("ID"));
        	String name = request.getParameter("name").toString();

        	String updateSQL = "UPDATE warehouse SET " +
        			" NAME = '"+ name + "'" + 
        			"WHERE ID = '" + ID + "'";

            warehousesDAO.update(updateSQL);
        	      	
        } catch (Throwable q) {
        	
        }
        return "redirect:/warehouses";	
	}
    
    @Scope("session")
    @RequestMapping(value="/delete_warehouse", method = RequestMethod.GET)
	public String delete_warehouses(Locale locale, Model model, HttpServletRequest request){
    	
        try {
        	
        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        	WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
        	
            int ID = Integer.parseInt(request.getParameter("id"));
            String delSQL = "DELETE FROM NUMBER WHERE ID = " + ID;
                        
            warehousesDAO.delete(delSQL);
        
        } catch (Throwable q) {
        	
        }
        return "redirect:/warehouses";	
	}
    
    @Scope("session")
    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String sel(Locale locale, Model model, HttpServletRequest request, HttpSession httpSession) {
        
        try {

        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        	WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
                     
            Map<Integer,HashMap<String,String>> ResultMap_WH = new HashMap<Integer, HashMap<String,String>>();
            Map<Integer,HashMap<String,String>> ResultMap_W = new HashMap<Integer, HashMap<String,String>>();

            ResultMap_WH = warehousesDAO.SQLquery("SELECT * FROM warehouse ");
            model.addAttribute("ResultMap_WH", ResultMap_WH);
            ResultMap_W = warehousesDAO.SQLquery("SELECT * FROM ware");
            model.addAttribute("ResultMap_W", ResultMap_W);

            
            return "search";
        } catch (Throwable t) {

        	//System.out.println(t);
            return "search";
            
        }
    	
    }
    
    @Scope("session")
    @RequestMapping(value="/search_list_w", method = RequestMethod.GET)
	public String searchList(Locale locale, Model model, HttpServletRequest request){
   	
        try {
        	
        	String sql = "SELECT * FROM number WHERE";
        	
			if (request.getParameter("ware") != "") {
				sql += " ware = " + request.getParameter("ware");
				
			}
						
        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
			Map<Integer,HashMap<String,String>> Result = new HashMap<Integer, HashMap<String,String>>();
        	WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");

            Result = warehousesDAO.SQLquery(sql);
	        model.addAttribute("Result", Result);		

	        return "res_search_wares";
	        
        } catch (Throwable q) {
	        return "res_search_wares";
        }
        //return "auth";	
	}
    
    @Scope("session")
    @RequestMapping(value="/search_list_wh", method = RequestMethod.GET)
	public String searchList_wh(Locale locale, Model model, HttpServletRequest request){
   	
        try {
        	
        	String sql = "SELECT * FROM number WHERE";
        	int WH = Integer.parseInt(request.getParameter("warehouse"));
        	
			if (WH != 0 ) {
				sql += " warehouse = " + WH;
				
			}
						
        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
			Map<Integer,HashMap<String,String>> Result = new HashMap<Integer, HashMap<String,String>>();
        	WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");

            Result = warehousesDAO.SQLquery(sql);
	        model.addAttribute("Result", Result);
	        model.addAttribute("WH_Res", WH);

        	
	        return "res_search_warehouses";
	        
        } catch (Throwable q) {
	        return "res_search_warehouses";
        }
        //return "auth";	
	}
    
    @RequestMapping("/move_ware")
    public String move_ware(HttpServletRequest request, Model model ) {
	 	int ID = Integer.parseInt(request.getParameter("id"));
	 	
	 	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        //Map<String,String> whouse = new HashMap<String,String>();
   	 
        WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
            
        Map<Integer,HashMap<String,String>> ResultMap = new HashMap<Integer, HashMap<String,String>>();
        ResultMap = warehousesDAO.SQLquery("SELECT ware.name as name, number.wnumber as wnumber, number.id, number.ware, number.warehouse "+
											" FROM  `ware` " +
											 " left join `number` "+
											 " On number.ware=ware.id " +
											 " Where number.id = " +ID);
        
        Map<Integer,HashMap<String,String>> ResultMap_WH = new HashMap<Integer, HashMap<String,String>>();
        ResultMap_WH = warehousesDAO.SQLquery("SELECT * FROM warehouse ");
        
        model.addAttribute("ResultMap_WH", ResultMap_WH);
        model.addAttribute("ResultMap", ResultMap.get(0));
        
		/**
		 * 	<c:forEach items="${countries}" var="country">
	     * 	<option value="${country.key}">${country.value}</option>
	     *	</c:forEach>
		 */
		return "move_ware";
    }
    
    @Scope("session")
    @RequestMapping(value="/save_move_ware", method = RequestMethod.POST)
	public String moveWare(Locale locale, Model model, HttpServletRequest request){
    	
        try {
        	
        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        	WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
        	
           // int ware_id = Integer.parseInt(request.getParameter("ware_id"));
           // int warehouse = Integer.parseInt(request.getParameter("warehouse"));
            int id = Integer.parseInt(request.getParameter("id"));
        	int number = Integer.parseInt(request.getParameter("wnumber"));

        	String updateSQL = "UPDATE number SET " +
        			" wnumber = wnumber - "+ number + "" + 
        			" WHERE id = " + id;

            warehousesDAO.update(updateSQL);          
        	      	
        } catch (Throwable q) {
        	
        }
        return "redirect:/search";	
	}
    
    @Scope("session")
    @RequestMapping(value="/delete_ware_from", method = RequestMethod.GET)
	public String delete_ware_from_warehouse(Locale locale, Model model, HttpServletRequest request){
    	
        try {
        	
        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        	WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
        	
            int ID = Integer.parseInt(request.getParameter("id"));

            String delSQL = "DELETE FROM NUMBER WHERE ID = " + ID;
            
            warehousesDAO.delete(delSQL);
        
        } catch (Throwable q) {
        	
        }
        return "redirect:/search";	
	} 
    
    @Scope("session")
    @RequestMapping(value="/custom", method = RequestMethod.GET)
	public String custom(Locale locale, Model model, HttpServletRequest request){
    	
        try {
        	
        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        	WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
                     
            Map<Integer,HashMap<String,String>> ResultMap_WH = new HashMap<Integer, HashMap<String,String>>();
            Map<Integer,HashMap<String,String>> ResultMap_W = new HashMap<Integer, HashMap<String,String>>();

            ResultMap_WH = warehousesDAO.SQLquery("SELECT * FROM warehouse ");
            model.addAttribute("ResultMap_WH", ResultMap_WH);
            ResultMap_W = warehousesDAO.SQLquery("SELECT * FROM ware");
            model.addAttribute("ResultMap_W", ResultMap_W);
            
        	      	
        } catch (Throwable q) {
        	
        }
        return "custom";	
	}
    
    @Scope("session")
    @RequestMapping(value="/save_custom", method = RequestMethod.POST)
	public String save_custom(Locale locale, Model model, HttpServletRequest request){
    	
        try {
        	
        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        	WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
        	
            int id_ware_custom = Integer.parseInt(request.getParameter("id_ware_custom"));
            int warehouse = Integer.parseInt(request.getParameter("warehouse"));
            //int id = Integer.parseInt(request.getParameter("id"));
        	int number = Integer.parseInt(request.getParameter("wnumber"));
          
            String insertSQL = "INSERT INTO NUMBER " +
				" (ware, warehouse, wnumber, flag) VALUES (" + id_ware_custom + "," + warehouse + "," + number + "," + 1 +" ) ";

            warehousesDAO.insert(insertSQL);
            
        	      	
        } catch (Throwable q) {
        	
        }
        return "redirect:/dashboard";	
	}
    
    @RequestMapping("/add_warehouse")
    public String add_warehouse( Model model, HttpServletRequest request) {
    	try{
    	//ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    	//WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
                 
       // Map<Integer,HashMap<String,String>> ResultMap_WH = new HashMap<Integer, HashMap<String,String>>();

        //ResultMap_WH = warehousesDAO.SQLquery("SELECT * FROM warehouse ");
        //model.addAttribute("ResultMap_WH", ResultMap_WH);
		return "add_warehouse";
		
    }
    	catch (Throwable q) {
    		return "warehouses";
    	}
    }
    
    @Scope("session")
    @RequestMapping(value="/save_warehouse", method = RequestMethod.POST)
	public String save_ware(Locale locale, Model model, HttpServletRequest request){
    	
        try {
        	
        	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        	WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
        	
        	String name = request.getParameter("name").toString();
        	String address = request.getParameter("address").toString();

            //System.out.println(name);
        	
            String insertSQL = "INSERT INTO warehouse " +
				" (name, address) VALUES ('" + name + "','" + address + "' ) ";

            warehousesDAO.insert(insertSQL);
       	      	
        } catch (Throwable q) {
        	
        }
        return "redirect:/warehouses";	
	}
    
    @Scope("session")
    @RequestMapping("/add_ware_to_wh")
    public String add_ware_to_wh(Locale locale, Model model, HttpServletRequest request) {
    	try{
    		
    		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        	WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
        	
        	int wh_id = Integer.parseInt(request.getParameter("WH_id"));
            System.out.println(wh_id);

        	
        	Map<Integer,HashMap<String,String>> ResultMap = new HashMap<Integer, HashMap<String,String>>();
            ResultMap = warehousesDAO.SQLquery("SELECT * FROM WAREHOUSE WHERE ID = " + wh_id);
            
        	model.addAttribute("WH_Res", ResultMap.get(0));

    		return "ware_to_wh";
		
        }
        	catch (Throwable q) {
        		
        	}
    	return "ware_to_wh";
    }
        
        @Scope("session")
        @RequestMapping(value="/save_add_ware", method = RequestMethod.POST)
    	public String save_ware_in_wh(Locale locale, Model model, HttpServletRequest request){
        	
            try {
            	
            	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
            	WarehousesDAO warehousesDAO = (WarehousesDAO) context.getBean("warehousesDAO");
            	
            	//String name = request.getParameter("name").toString();
            	//String address = request.getParameter("address").toString();

            	int wh_id = Integer.parseInt(request.getParameter("WH_id"));
            	int wh_ware_id = Integer.parseInt(request.getParameter("id_ware_add"));
            	int wh_wnumber = Integer.parseInt(request.getParameter("wnumber_ware_add"));

            	
                String insertSQL = "INSERT INTO number " +
    				" (ware, warehouse, wnumber, flag) VALUES (" + wh_ware_id + "," + wh_id + "," + wh_wnumber + "," + 0 + " ) ";

                warehousesDAO.insert(insertSQL);
           	      	
            } catch (Throwable q) {
            	
            }
            return "redirect:/search";	
    	}
    
}
