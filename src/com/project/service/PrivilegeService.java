/**
 * 
 */
package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.domain.PageView;
import com.project.domain.Privilege;
import com.project.domain.PrivilegePK;

/**
 * @author dingjs
 *
 */
public interface PrivilegeService {
	public void update(Privilege privilege);
	
	public void save(Privilege privilege);
	
	public List<Privilege> findAll();
	
	public PageView<Privilege> getPageViewList(Map<String, String> params, Integer pageSize, Integer currentPage);
	
	public Privilege getById(PrivilegePK privilegePK);
	
	public List<Privilege> findTopPrivilegeList();
	
	public void delete(PrivilegePK privilegePK);
}
