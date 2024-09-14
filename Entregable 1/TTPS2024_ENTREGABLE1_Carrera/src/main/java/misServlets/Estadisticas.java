package misServlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Estadisticas
 */
public class Estadisticas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Estadisticas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ServletContext contexto = request.getServletContext();
		
		
		out.println( "<html><body>");
		out.println("<h1> Cantidad de abonos vendidos de Dos dias: " + (Integer.parseInt(contexto.getInitParameter("DosDias")) - Integer.parseInt(contexto.getAttribute("DosDias").toString())) + " </h1>");
		out.println("<h1> Cantidad de abonos vendidos de Tres dias: " + (Integer.parseInt(contexto.getInitParameter("TresDias")) - Integer.parseInt(contexto.getAttribute("TresDias").toString())) + " </h1>");		
		out.println("<a href='./index.html'>Volver al menú principal</a>");
		out.print("</body></html>");
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
