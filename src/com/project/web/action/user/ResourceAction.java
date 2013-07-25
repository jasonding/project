/**
 * 
 */
package com.project.web.action.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.project.domain.PageView;
import com.project.domain.mapping.Resource;
import com.project.service.ResourceService;
import com.project.util.NullUtil;
import com.project.web.action.BaseAction;

/**
 * @author dingjs
 */
@Controller("ResourceAction")
@Scope("prototype")
public class ResourceAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ResourceService resourceService;
	
	private Resource resource;
	
	//数据列表需要的参数数据
	private Map<String,String> params;
	private Integer pageSize;
	private Integer currentPage;
	private PageView<Resource> pageView;

	public String list() {
		//pageView = resourceService.getPageViewList(params, pageSize, currentPage);
		return LIST;
	}
	
	public String listMenu() {
		
		return LIST;
	}
	
	public String manage() {
		resourceService.save(resource);
		return SUCCESS;
	}
	
	public String input() {
		if(!NullUtil.isNull(resource) && !NullUtil.isNull(resource.getId())) {
			resource = resourceService.getById(resource.getId());
		}
		return INPUT;
	}
	
	public String delete() {
		resourceService.delete(resource);
		return SUCCESS;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
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

	public PageView<Resource> getPageView() {
		return pageView;
	}

	@Autowired
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
}
