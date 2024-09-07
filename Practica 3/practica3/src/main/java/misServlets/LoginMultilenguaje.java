package misServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Servlet implementation class LoginMultilenguaje
 */
public class LoginMultilenguaje extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResourceBundle archivo;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginMultilenguaje() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String lenguaje = (String)request.getAttribute("lenguaje");
		this.getArchivo(lenguaje);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(this.generateMenu());
		out.close();
	}
	
	private String generateMenu() {
		return "<html><body>"
				+ "<h1>" + this.archivo.getString("title") + " </h1>"
				+ "<h1>" + this.archivo.getString("labelusuario") + " </h1>"
				+ "<h1>" + this.archivo.getString("labelpassword") + " </h1>"
				+  "</body></html>";
	}	
	private void getArchivo(String lenguaje) {
		Locale.setDefault(new Locale(lenguaje));
		this.archivo = ResourceBundle.getBundle("texto");
//		if (lenguaje.equals("es")) this.archivo = ResourceBundle.getBundle("texto", Locale.forLanguageTag(lenguaje));
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
