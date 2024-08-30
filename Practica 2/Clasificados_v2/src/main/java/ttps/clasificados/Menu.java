package ttps.clasificados;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Menu
 */
public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Menu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Usuario perfil = (Usuario) request.getAttribute("user");
		
		out.println( "<html><body>");
		out.println("<h1> Hola " + perfil.getNombre() + " </h1>");
		out.println(this.dynamicMenu(perfil.getPerfil()));		
		out.println(this.incluirEncabezado(request, response));	
		out.print(" </body></html> ");
		out.close();
	
	}
	
	protected String incluirEncabezado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Encabezado");
		if (dispatcher != null) {			
			dispatcher.include(request, response); 
			return "<div><h4>" + request.getAttribute("imprimir").toString() + "";
		}
		return "";
	}
	
	protected String dynamicMenu(String perfil) {
		if (perfil.equals("Administrador")) {
			return "<div><h4>Imaginemos un menu administrador</h4></div>";
		}
		return "<div><h4>Imaginemos un menu publicador</h4></div>";
	}

}
