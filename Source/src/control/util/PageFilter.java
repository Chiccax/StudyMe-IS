package control.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelBean.UtenteBean;



@WebFilter(urlPatterns = {"/InsegnanteServlet", "/GestoreServlet","/LezioneInsegnante.jsp"})
public class PageFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = null;
		HttpServletResponse httpResponse = null;
		
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			httpRequest = (HttpServletRequest) request;
			httpResponse = (HttpServletResponse) response;
		}
		
		if(httpRequest != null) {
			httpRequest = (HttpServletRequest)request;
			UtenteBean utente = (UtenteBean) httpRequest.getSession().getAttribute("insegnante");
			UtenteBean utente2 = (UtenteBean) httpRequest.getSession().getAttribute("gestorecatalogo");
			
			if(utente == null) {
				httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}else 
				if(utente2 == null) {
					httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
					return;
				}
		}	
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

}

