package ttps.clasificados;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ttps.clasificados.SitioClasificado;

@WebListener
public class Listener implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent sce) { 	}

	public void contextInitialized(ServletContextEvent sce) {
		// Se leen parametros de inicializacion de la aplicación
		String nombre = sce.getServletContext().getInitParameter("nombre");
		String mail = sce.getServletContext().getInitParameter("mail");
		String telefono = sce.getServletContext().getInitParameter("telefono");	
		
		ServletContext contexto = sce.getServletContext();
		SitioClasificado sitio = new SitioClasificado(nombre,mail,telefono);
		contexto.setAttribute("sitio", sitio);
	}
}
