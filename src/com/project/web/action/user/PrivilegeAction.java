/**
 * 
 */
package com.project.web.action.user;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.project.domain.PageView;
import com.project.domain.Privilege;
import com.project.service.PrivilegeService;
import com.project.web.action.BaseAction;

/**
 * @author dingjs
 */
@Controller("PrivilegeAction")
@Scope("prototype")
public class PrivilegeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private PrivilegeService privilegeService;
	
	private Privilege privilege;
	
	
	//数据列表需要的参数数据
	private Map<String,String> params;
	private Integer pageSize;
	private Integer currentPage;
	private PageView<Privilege> pageView;
	
	private List<Privilege> parentPrivilegeList;

	public String list() {
		return LIST;
	}
	
	public String manage() {
		return SUCCESS;
	}
	
	public String input() {
		return INPUT;
	}
	
	public String delete() {
		return SUCCESS;
	}
}
