/**
 * 
 */
package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.domain.PageView;
import com.project.domain.mapping.Resource;
import com.project.enumeration.ResourceType;

/**
 * @author dingjs
 *
 */
public interface ResourceService {
	public void update(Resource resource);
	
	public void save(Resource resource);
	
	public List<Resource> findAll();
	
	public PageView<Resource> getPageViewList(Map<String, String> params, Integer pageSize, Integer currentPage);
	
	public PageView<Resource> getPageViewList(Map<String, String> params, List<Resource> resourceList);
	
	public List<Resource> getMenu();
	
	public Resource getById(Long id);
	
	public void delete(Resource resource);
	
	
}
