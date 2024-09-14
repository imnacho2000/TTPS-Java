package misListeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ListenerCupos
 *
 */
public class ListenerCupos implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ListenerCupos() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	String cuposDosDias = sce.getServletContext().getInitParameter("DosDias");
    	String cuposTresDias = sce.getServletContext().getInitParameter("TresDias");		
    	
    	ServletContext contexto = sce.getServletContext();
    	
    	contexto.setAttribute("DosDias", cuposDosDias);
    	contexto.setAttribute("TresDias", cuposTresDias);
    
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub

    }
	
}
