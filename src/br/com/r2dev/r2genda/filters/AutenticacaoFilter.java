package br.com.r2dev.r2genda.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AutenticacaoFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		String usuarioLogado = (String) req.getSession().getAttribute("usuario");
		
		if (usuarioLogado != null) {
			chain.doFilter(request, response);
			return;
		}
		
		String contextPath = req.getContextPath();
		String uri = req.getRequestURI().replace(contextPath, "");
		List<String> urlsPermitidas = Arrays.asList("/login.jsp", "/login", "/logout");
		
		if (urlsPermitidas.contains(uri)) {
			chain.doFilter(request, response);
			return;
		}

		request.setAttribute("erro", "Acesso negado.");
		
		RequestDispatcher dispatcher = req.getServletContext()
				.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
