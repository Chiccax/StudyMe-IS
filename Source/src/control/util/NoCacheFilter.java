package control.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class NoCacheFilter
 */
@WebFilter("/*")
public class NoCacheFilter implements Filter {

public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if(response instanceof HttpServletResponse) {
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			httpResponse.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");	
		}
			
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

}
