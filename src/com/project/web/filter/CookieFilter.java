package com.project.web.filter;

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
				
			}
		}
		
		chain.doFilter(httpServletRequest, httpServletResponse);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
