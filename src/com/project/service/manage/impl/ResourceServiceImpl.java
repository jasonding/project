/**
 * 
 */
package com.project.service.manage.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.ResourceDao;
import com.project.domain.PageView;
import com.project.domain.mapping.Resource;
import com.project.service.manage.ResourceService;
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
	public List<Resource> getResourceOnlyMenu() {
		return resourceDao.getResourceOlnyMenu();
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
