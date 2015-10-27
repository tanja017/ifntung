package controllers;
import java.io.Console;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import controllers.HomeController;

/**
 * @author tsarev.oi@mail.ru
 *         User: Oleg Tsarev
 *         Date: 10.11.2010
 *         Time: 15:06:25
 *
 */
@Controller("headerController")
public class HeaderController extends ViewPreparerSupport{

	//Creating object of class, - for getting data from home controller
	HomeController Home = new HomeController();
	
    @Override
    public void execute(TilesRequestContext tilesContext, AttributeContext attributeContext ) {
    	
    	String userName;
    	try{
    		 userName =  Home.userInformation.get(0).toString();
    	} catch (Throwable t) {
    		 userName =  "User";
    	}
    	
    	System.out.println(userName);
        // Get access to model parameters from MainController as example
        String message = (String) tilesContext.getRequestScope().get("message");
        tilesContext.getRequestScope().put("headerMessage", userName);
        
    }

}
