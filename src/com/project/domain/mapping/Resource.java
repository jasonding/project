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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.domain.mapping.Privilege;
import com.project.enumeration.DisplayType;
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
	private DisplayType displayType;
	private String resourceName;
	private String resource;
	private int orderView;
	
	private Resource parentResource;
	private Set<Resource> childResourceSet = new HashSet<Resource>();
	private Set<Privilege> privilegeSet = new HashSet<Privilege>();

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="resource_type",length=4)
	public ResourceType getResourceType() {
		return resourceType;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="display_type",length=4)
	public DisplayType getDisplayType() {
		return displayType;
	}

	@Column(name="resource",length=500)
	public String getResource() {
		return resource;
	}

	@Column(name="resource_name",length=20)
	public String getResourceName() {
		return resourceName;
	}
	
	@Column(name="order_view",length=20)
	public int getOrderView() {
		return orderView;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	public Resource getParentResource() {
		return parentResource;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentResource", targetEntity = Resource.class)
	public Set<Resource> getChildResourceSet() {
		return childResourceSet;
	}

	@ManyToMany(mappedBy="resourceSet",fetch=FetchType.LAZY,targetEntity=Privilege.class)
	public Set<Privilege> getPrivilegeSet() {
		return privilegeSet;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOrderView(int orderView) {
		this.orderView = orderView;
	}
	
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	
	public void setDisplayType(DisplayType displayType) {
		this.displayType = displayType;
	}
	
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public void setResource(String resource) {
		this.resource = resource;
	}

	public void setParentResource(Resource parentResource) {
		this.parentResource = parentResource;
	}
	
	public void setChildResourceSet(Set<Resource> childResourceSet) {
		this.childResourceSet = childResourceSet;
	}
	
	public void setPrivilegeSet(Set<Privilege> privilegeSet) {
		this.privilegeSet = privilegeSet;
	}
	
	public boolean hasChildren() {
		if(getChildResourceSet().size() == 0) {
			return false;
		}else {
			return true;
		}
	}
}
