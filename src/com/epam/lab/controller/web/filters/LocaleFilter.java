package com.epam.lab.controller.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LocaleFilter
 */
@WebFilter("/*")
public class LocaleFilter implements Filter {
	private String browserLocalevalue = null;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);

		if (browserLocalevalue == null
				|| (!(browserLocalevalue.equals(request.getLocale().toString())))) {
			browserLocalevalue = request.getLocale().toString();
			if (session == null) {
				session = httpRequest.getSession(true);
			}
			session.setAttribute("sessLocale", request.getLocale());

		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

}
