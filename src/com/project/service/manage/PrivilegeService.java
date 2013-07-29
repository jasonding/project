/**
 * 
 */
package com.project.service.manage;

import java.util.List;
import java.util.Map;

import com.project.domain.PageView;
import com.project.domain.mapping.Privilege;

/**
 * @author dingjs
 *
 */
public interface PrivilegeService {
	public void update(Privilege privilege);
	
	public void save(Privilege privilege);
	
	public List<Privilege> findAll();
	
	public PageView<Privilege> getPageViewList(Map<String, String> params, Integer pageSize, Integer currentPage);
}
