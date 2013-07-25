/**
 * 
 */
package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.constant.Global;
import com.project.dao.PrivilegeDao;
import com.project.domain.PageView;
import com.project.domain.Privilege;
import com.project.domain.PrivilegePK;
import com.project.service.PrivilegeService;

/**
 * @author dingjs
 */
@Service("privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService {
	private PrivilegeDao privilegeDao;
	
	@Autowired
	public void setPrivilegeDao(PrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}

	public List<Privilege> findAll() {
		return privilegeDao.findAll();
	}

	public void save(Privilege privilege) {
		privilegeDao.merge(privilege);
	}

	public void update(Privilege privilege) {
		privilegeDao.update(privilege);
	}
	
	public void delete(PrivilegePK privilegePK) {
		Privilege privilege = privilegeDao.getById(privilegePK);
		privilegeDao.clearStagingTables(
				"delete from role_privilege where module=? and privilege=?", privilegePK.getModule(),privilegePK.getPrivilege());
		privilegeDao.delete(privilege);
	}

	public Privilege getById(PrivilegePK privilegePK) {
		return privilegeDao.getById(privilegePK);
	}

	public PageView<Privilege> getPageViewList(Map<String, String> params,
			Integer pageSize, Integer currentPage) {
		if(pageSize == null) {
			pageSize = Global.INTEGER_DEFAULT_PAGE_SIZE;
		}
		if(currentPage == null) {
			currentPage = Global.INTEGER_DEFAULT_CURRENT_PAGE;
		}
		Integer recordCount = privilegeDao.getCount(params);
		List<Privilege> recordList = privilegeDao.getPageList(params,PageView.calcFirstResult(currentPage, pageSize),pageSize);
		return new PageView<Privilege>(pageSize, currentPage, recordCount, recordList);
	}

	public List<Privilege> findTopPrivilegeList() {
		return privilegeDao.findTopPrivilegeList();
	}
	
}
