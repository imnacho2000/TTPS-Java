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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet Filter implementation class FiltroLenguajeCliente
 */
public class FiltroLenguajeCliente extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FiltroLenguajeCliente() {
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
		
		String lenguaje = req.getHeader("Accept-Language");
		lenguaje = getIdioma(lenguaje);
		req.setAttribute("lenguaje", lenguaje);
		System.out.println(lenguaje);
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
	}
	
	private String getIdioma(String lenguaje) {
        Pattern pattern = Pattern.compile("^([^,;]+)");
        Matcher matcher = pattern.matcher(lenguaje);

        if (matcher.find()) {
            String firstLanguage = matcher.group(1);
            if (firstLanguage.contains("es-419")) firstLanguage = "es";
            return firstLanguage; // Imprimirá: es-AR
        } 
        return "";
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
