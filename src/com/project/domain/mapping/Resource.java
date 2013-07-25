/**
 * 
 */
package com.project.domain.mapping;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.project.domain.Privilege;
import com.project.enumeration.ResourceType;

/**
 * @author dingjs
 */
@Entity
@Table(name = "resource")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private ResourceType resourceType;
	private String resource;
	private String resourceName;
	private int orderView;
	
	private Set<Privilege> privilegeSet = new HashSet<Privilege>();

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name="resource_type",length=2)
	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	
	@Column(name="resource",length=200)
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	@Column(name="resource_name",length=20)
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@ManyToMany(mappedBy="resourceSet",fetch=FetchType.LAZY,targetEntity=Privilege.class)
	public Set<Privilege> getPrivilegeSet() {
		return privilegeSet;
	}

	public void setPrivilegeSet(Set<Privilege> privilegeSet) {
		this.privilegeSet = privilegeSet;
	}

	@Column(name="order_view",length=20)
	public int getOrderView() {
		return orderView;
	}

	public void setOrderView(int orderView) {
		this.orderView = orderView;
	}
}
