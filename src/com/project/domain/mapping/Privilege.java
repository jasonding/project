/**
 * 
 */
package com.project.domain.mapping;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author dingjs
 */
@Entity
@Table(name = "privilege")
public class Privilege implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Set<Resource> resourceSet =  new HashSet<Resource>();
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="name",length=20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(fetch=FetchType.EAGER,targetEntity=Resource.class)
	@JoinTable(name="privilege_resource",
		 joinColumns=@JoinColumn(name="privilege_id"),
		   inverseJoinColumns=@JoinColumn(name="resource_id")
	)
	public Set<Resource> getResourceSet() {
		return resourceSet;
	}
	
	public void setResourceSet(Set<Resource> resourceSet) {
		this.resourceSet = resourceSet;
	}
}
