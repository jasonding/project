package com.project.service.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.constant.WebConstant;
import com.project.util.NullUtil;

public class ValidateRandomCodeFilter implements Filter {

	private String filterProcessesUrl;

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if(!requiresAuthentication(httpRequest)) {
			chain.doFilter(request, response);
			return ;
		}
		
		String randomCode = httpRequest.getParameter("randomCode");
		String sessionCode = (String) httpRequest.getSession().getAttribute(WebConstant.WEB_RANDOM_CODE);
		if(NullUtil.isNullOrEmpty(randomCode) || !randomCode.equalsIgnoreCase(sessionCode)) {
			HttpSession session = httpRequest.getSession(false);
	        if (session != null) {
	        	httpRequest.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", "验证码不正确");
	        }
	        httpResponse.sendRedirect(httpRequest.getContextPath() + "/input.do");
		}else {
			chain.doFilter(request, response);
		}
	}

	protected boolean requiresAuthentication(HttpServletRequest request) {
		String uri = request.getRequestURI();
		
		int pathParamIndex = uri.indexOf(';');
		if (pathParamIndex > 0) {
			uri = uri.substring(0, pathParamIndex);
		}
		
		pathParamIndex = uri.indexOf('?');
		if (pathParamIndex > 0) {
			uri = uri.substring(0, pathParamIndex);
		}

		if ("".equals(request.getContextPath())) {
			return uri.endsWith(filterProcessesUrl);
		}

		return uri.endsWith(request.getContextPath() + filterProcessesUrl);
	}

	public String getFilterProcessesUrl() {
		return filterProcessesUrl;
	}

	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

}
