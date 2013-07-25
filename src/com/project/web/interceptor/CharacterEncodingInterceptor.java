package com.project.web.interceptor;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.project.constant.WebConstant;


public class CharacterEncodingInterceptor extends AbstractInterceptor implements StrutsStatics{
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ationContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) ationContext.get(HTTP_REQUEST);
		if(WebConstant.WEB_POST.equalsIgnoreCase(request.getMethod())) {
			return invocation.invoke();
		}
		request.setCharacterEncoding("UTF-8");
		Map<String, Object> parameters = ationContext.getParameters();
		Map<String,Object> encodingMap = new HashMap<String, Object>();
		for(Map.Entry<String, Object> entry : parameters.entrySet()) {
			String key = entry.getKey();
			String[] strs = (String[]) entry.getValue();
			String[] encodingStrs = new String[strs.length];
			for (int i = 0; i < strs.length; i++) {
				String decodeStr = URLDecoder.decode(strs[i], "UTF-8");
				decodeStr = new String(decodeStr.getBytes("ISO8859-1"),"UTF-8");
				encodingStrs[i] = decodeStr;
			}
			encodingMap.put(key, encodingStrs);
		}
		ationContext.setParameters(encodingMap);
		return invocation.invoke();
	}
}
