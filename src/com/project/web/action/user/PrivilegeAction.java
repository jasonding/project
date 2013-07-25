/**
 * 
 */
package com.project.web.action.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.project.domain.PageView;
import com.project.domain.Privilege;
import com.project.service.PrivilegeService;
import com.project.util.NullUtil;
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
		pageView = privilegeService.getPageViewList(params, pageSize, currentPage);
		return LIST;
	}
	
	public String manage() {
		privilegeService.save(privilege);
		return SUCCESS;
	}
	
	public String input() {
		if(!NullUtil.isNull(privilege) &&  
				!NullUtil.isNull(privilege.getPrivilegePK())) {
			privilege = privilegeService.getById(privilege.getPrivilegePK());
		}
		//List<Privilege> findTopPrivilegeList = privilegeService.findTopPrivilegeList();
		parentPrivilegeList = privilegeService.findTopPrivilegeList();
		//parentPrivilegeList =  PrivilegeTreeUtil.getPrivilegeTreeList(findTopPrivilegeList);
		return INPUT;
	}
	
	public String delete() {
		privilegeService.delete(privilege.getPrivilegePK());
		return SUCCESS;
	}

	@Autowired
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public PageView<Privilege> getPageView() {
		return pageView;
	}

	public void setPageView(PageView<Privilege> pageView) {
		this.pageView = pageView;
	}

	public List<Privilege> getParentPrivilegeList() {
		return parentPrivilegeList;
	}

	public void setParentPrivilegeList(List<Privilege> parentPrivilegeList) {
		this.parentPrivilegeList = parentPrivilegeList;
	}
}
