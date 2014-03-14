package com.project.web.filter;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.constant.WebConstant;
import com.project.domain.game.GameUser;
import com.project.util.RamCacheUtil;

public class CookieFilter implements Filter {

	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		Cookie[] cookies = httpServletRequest.getCookies();
		boolean flag = false;
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("gameUser")) {
					flag = true;
					String value = cookie.getValue();
				}
			}
		}
		
		HttpSession session = httpServletRequest.getSession();
		String sessionId = session.getId();
		if(session != null) {
			GameUser gameUser = (GameUser) session.getAttribute(WebConstant.WEB_GAME_USER);
			if(gameUser != null) {
				//其他浏览器id
				Object cacheSessionId = RamCacheUtil.getCache(gameUser.getName());
				if( !sessionId.equals(cacheSessionId) ) {
					session.removeAttribute(WebConstant.WEB_GAME_USER);
					httpServletResponse.getWriter().write("其他地方已经登录，您被迫下线");
					return ;
				}
				Cookie cookie = new Cookie("JSESSIONID", sessionId);
				cookie.setPath("/project");
				cookie.setMaxAge(1000);
				Cookie gameUserCookie = new Cookie("gameUser", gameUser.getName() + "_" + gameUser.getPassword());
				gameUserCookie.setPath("/project");
				gameUserCookie.setMaxAge(1000);
				
				httpServletResponse.addCookie(cookie);
				httpServletResponse.addCookie(gameUserCookie);
			}
			
		}
		
		chain.doFilter(httpServletRequest, httpServletResponse);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
