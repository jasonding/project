/**
 * 
 */
package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.ResourceDao;
import com.project.domain.PageView;
import com.project.domain.mapping.Resource;
import com.project.enumeration.ResourceType;
import com.project.service.ResourceService;
import com.project.util.NullUtil;

/**
 * @author dingjs
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	private ResourceDao resourceDao;
	
	@Autowired
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Override
	public List<Resource> findAll() {
		return resourceDao.findAll();
	}

	@Override
	public Resource getById(Long id) {
		return resourceDao.getById(id);
	}

	@Override
	public PageView<Resource> getPageViewList(Map<String, String> params, Integer pageSize, Integer currentPage) {
		return null;
	}

	@Override
	public List<Resource> getMenu() {
		return null;
	}

	@Override
	public PageView<Resource> getPageViewList(Map<String, String> params,
			List<Resource> resourceList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Resource resource) {
		if(NullUtil.isNull(resource)) return ;
		resourceDao.save(resource);
	}

	@Override
	public void update(Resource resource) {
		if(NullUtil.isNull(resource)) return ;
		resourceDao.update(resource);
		
	}

	@Override
	public void delete(Resource resource) {
		if(NullUtil.isNull(resource)) return ;
		resourceDao.delete(resource);
	}
}
