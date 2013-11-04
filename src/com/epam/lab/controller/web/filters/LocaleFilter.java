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
import com.epam.lab.controller.services.locale.LocaleServiceImpl;

/**
 * Servlet Filter implementation class LocaleFilter
 */
@WebFilter({ "/signin", "/signout", "/homepage", "/userpage", "/pricing",
		"/about", "/team", "/settings", "/EditUserLoginServlet",
		"/EditEmailServlet", "/adminUsersPage", "/employeeControllerUsers",
		"/adminTariffsPage", "/employeeControllerTariffs",
		"/adminStatisticsPage", "/adminLogsPage", "/adminUser",
		"/userEmployeeController", "/adminUserActivity",
		"/adminUserActivityGraph", "/EditUserLoginServlet", "/EditEmailServlet" })
public class LocaleFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(true);
		LocaleServiceImpl locImpl = new LocaleServiceImpl();

		if ((session.getAttribute("sessLocale").toString().equals("") && session
				.getAttribute("currbrowsLang").toString().equals(""))
				|| (!session.getAttribute("currbrowsLang").toString()
						.equals("") && (!(session.getAttribute("currbrowsLang")
						.toString().equals(request.getLocale().toString()))))) {
			session.setAttribute("currbrowsLang", request.getLocale());
			session.setAttribute("sessLocale", request.getLocale());
		}

		session.setAttribute("currentLanguage", locImpl.getByLocale(session
				.getAttribute("sessLocale").toString()));
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

}