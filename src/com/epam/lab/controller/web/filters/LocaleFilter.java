package com.epam.lab.controller.web.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.language.LanguageServiceImpl;
import com.epam.lab.controller.services.locale.LocaleServiceImpl;
import com.epam.lab.model.Language;

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
		LocaleServiceImpl locImpl = new LocaleServiceImpl();
		LanguageServiceImpl impl = new LanguageServiceImpl();

		if (browserLocalevalue == null
				|| (!(browserLocalevalue.equals(request.getLocale().toString())))) {
			browserLocalevalue = request.getLocale().toString();
			if (session == null) {
				session = httpRequest.getSession(true);
			}
			session.setAttribute("sessLocale", request.getLocale());

		}
		session.setAttribute("currentLanguage", locImpl.getByLocale(session
				.getAttribute("sessLocale").toString()));
		List<Language> list = impl.getAll();
		session.setAttribute("languages", list);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

}
