package com.project.web.action;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final long serialVersionUID = 1L;
	
	public static final String LIST = "list";
	
	/**
	 * 得到服务器session
	 * @return
	 */
	protected Map<String,Object> getSession() {
		return ActionContext.getContext().getSession();
	}
}
