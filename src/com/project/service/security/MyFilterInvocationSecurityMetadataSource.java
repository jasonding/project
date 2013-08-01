package com.project.service.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.RequestKey;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.stereotype.Repository;

import com.project.domain.mapping.Privilege;
import com.project.domain.mapping.Resource;
import com.project.domain.mapping.Role;
import com.project.service.manage.ResourceService;

@Repository(value="myFilterInvocationSecurityMetadataSource")
public class MyFilterInvocationSecurityMetadataSource implements
		FactoryBean<DefaultFilterInvocationSecurityMetadataSource> {

	private ResourceService resourceService;
	
	@Autowired
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	@Override
	public DefaultFilterInvocationSecurityMetadataSource getObject() throws Exception {
		List<Resource> resourceList = resourceService.getResourceNoMenuWithRole();
		LinkedHashMap<RequestKey, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestKey, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> attributes =null;
		for (Resource resource : resourceList) {
			RequestKey requestKey = new RequestKey(resource.getResource());
			attributes = new HashSet<ConfigAttribute>();
			for (Privilege privilege : resource.getPrivilegeSet()) {
				for (Role role : privilege.getRoleSet()) {
					attributes.add(new SecurityConfig(role.getAuthority()));
				}
			}
			
			requestMap.put(requestKey, attributes);
		}
		
		
		return new DefaultFilterInvocationSecurityMetadataSource(new AntUrlPathMatcher(), requestMap);
	}

	@Override
	public Class<?> getObjectType() {
		return DefaultFilterInvocationSecurityMetadataSource.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
