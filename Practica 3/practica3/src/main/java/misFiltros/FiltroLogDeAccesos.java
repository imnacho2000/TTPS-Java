package misFiltros;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Servlet Filter implementation class FiltroLogDeAccesos
 */
public class FiltroLogDeAccesos extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FiltroLogDeAccesos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest  req =(HttpServletRequest ) request;
		HttpServletResponse res =(HttpServletResponse) response;
	
		// pass the request along the filter chain
		req.setAttribute("log", armarHtml(req));
	
		chain.doFilter(req, res);				
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("volvi al filtro, luego de haber ido al Servlet a imprimir los datos.");
		out.close();
	}
	
	private String obtenerFechaYHora() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
	}
	
	private String armarHtml(HttpServletRequest req) {
		return "<body>\r\n"
				+ "	<h1>Log: </h1>\r\n"
				+ "<ul>\r\n"
				+ "		<li>Ip:"  + req.getRemoteAddr() + "</li>\r\n"
				+ "		\r\n"
				+ "		<li>Fecha y hora: " + this.obtenerFechaYHora() +"</li>\r\n"
				+ "		\r\n"
				+ "		<li>Request line: </li>\r\n"
				+ "		<li>User-Agent: </li>\r\n"
				+ "	</ul>"
				+ "</body>";
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
