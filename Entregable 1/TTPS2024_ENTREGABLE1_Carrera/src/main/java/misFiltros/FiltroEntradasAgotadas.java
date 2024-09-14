package misFiltros;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet Filter implementation class FiltroEntradasAgotadas
 */
public class FiltroEntradasAgotadas extends HttpFilter implements Filter {
    private int cuponOtroInt, cuponSelect;
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FiltroEntradasAgotadas() {
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
        this.generoSeleccionCupos(request, req);
        
        System.out.println(cuponSelect + " " + cuponOtroInt);
        if (cuponSelect - 1 < 0  && cuponOtroInt - 1 >= 0) {
        	req.getRequestDispatcher("/HayOtrasEntradas.html").forward(req, res);
            return; 
        }
        else if (cuponSelect - 1 < 0  && cuponOtroInt - 1 < 0) {
            req.getRequestDispatcher("/noHayEntradas.html").forward(req, res);
            return;
        }
     
		// pass the request along the filter chain
		chain.doFilter(req, res);
	}
	
	private String decidoDia(String cuponSelect) {
		if (cuponSelect.contains("Dos")) {
			
        	return "TresDias";
        }
        else if (cuponSelect.contains("Tres")) {
        	return "DosDias";
        }
		return "";
	}
	
	private void generoSeleccionCupos(ServletRequest request,HttpServletRequest req) {
		String cuponSeleccionado = req.getParameter("cupos");
		System.out.println(cuponSeleccionado);
		
		ServletContext sce = request.getServletContext();
        this.cuponSelect = Integer.parseInt(sce.getAttribute(cuponSeleccionado).toString());
        
        String cuponOtroString = decidoDia(cuponSeleccionado);
        this.cuponOtroInt = Integer.parseInt(sce.getAttribute(cuponOtroString).toString());
        
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
