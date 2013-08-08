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

	public static final String SPRING_SECURITY_RANDOM_CODE_KEY = "SPRING_SECURITY_RANDOM_CODE";
	public static final String SPRING_SECURITY_FORM_RANDOMCODE_KEY = "j_randomCode";
	
	private String filterProcessesUrl;
	private String filterRedirectUrl;

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

		
		httpRequest.getSession().removeAttribute(SPRING_SECURITY_RANDOM_CODE_KEY);
		
		if(!requiresAuthentication(httpRequest)) {
			chain.doFilter(request, response);
			return ;
		}
		
		String randomCode = httpRequest.getParameter(SPRING_SECURITY_FORM_RANDOMCODE_KEY);
		String sessionCode = (String) httpRequest.getSession().getAttribute(WebConstant.WEB_RANDOM_CODE);
		if(NullUtil.isNullOrEmpty(randomCode) || !randomCode.equalsIgnoreCase(sessionCode)) {
			HttpSession session = httpRequest.getSession(false);
	        if (session != null) {
	        	httpRequest.getSession().setAttribute(SPRING_SECURITY_RANDOM_CODE_KEY, "验证码不正确");
	        }
	        httpRequest.getSession().removeAttribute(WebConstant.WEB_RANDOM_CODE);
	        httpResponse.sendRedirect(httpRequest.getContextPath() + getFilterRedirectUrl());
		}else {
			httpRequest.getSession().removeAttribute(WebConstant.WEB_RANDOM_CODE);
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
			return uri.endsWith(getFilterProcessesUrl());
		}

		return uri.endsWith(request.getContextPath() + getFilterProcessesUrl());
	}

	public String getFilterProcessesUrl() {
		return filterProcessesUrl;
	}

	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	public String getFilterRedirectUrl() {
		return filterRedirectUrl;
	}

	public void setFilterRedirectUrl(String filterRedirectUrl) {
		this.filterRedirectUrl = filterRedirectUrl;
	}

}
