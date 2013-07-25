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
import com.project.service.ResourceService;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<Resource> getPageViewList(Map<String, String> params,
			Integer pageSize, Integer currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Resource resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Resource resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Resource resource) {
		// TODO Auto-generated method stub
		
	}
}
