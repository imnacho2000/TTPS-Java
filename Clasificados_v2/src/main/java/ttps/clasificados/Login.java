package ttps.clasificados;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ttps.clasificados.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Usuario> users = new ArrayList<Usuario>();
	private static String nombre;
	private static String clave;
	private static String perfil;

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    	super();
    	Usuario fedigree = new Usuario("fedigree","fandeBertone32","Administrador");
    	Usuario nacho = new Usuario("niqobmx","Bertone32","Publicador");
    	Usuario franco = new Usuario("Franco7009","contraseña","Administrador");
    	
    	users.add(fedigree);
    	users.add(nacho);
    	users.add(franco);
 
    	
    }
    
    protected Optional<Usuario> checkearLogin(List<Usuario> users, String nombre, String clave) {
    	return this.users.stream().filter(p -> p.getNombre().equals(nombre) && p.getClave().equals(clave)).findFirst();
    }

    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
			
		this.nombre = request.getParameter("nombre");
		this.clave = request.getParameter("contra");
		
		if (this.checkearLogin(users, nombre, clave).isPresent()) {
//			System.out.println("Ingrese");
			Usuario user = this.checkearLogin(users, nombre, clave).get();
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Menu"); 
			if (dispatcher != null) {
				request.setAttribute("user",user);
				dispatcher.forward(request, response);
			}
			
		}
		else {
			response.sendRedirect("Error.html");
		}
		
		
	}

}
