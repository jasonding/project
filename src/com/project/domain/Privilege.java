/**
 * 
 */
package com.project.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.project.domain.mapping.Role;
import com.project.enumeration.ResourceType;

/**
 * @author dingjs
 */
@Entity
@Table(name = "privilege")
public class Privilege implements Serializable,Comparable<Privilege> {
	private static final long serialVersionUID = 1L;

	/* 权限主键pk */
	private PrivilegePK privilegePK;

	/* 权限名称 */
	private String name;

	/* 权限类型 */
	private ResourceType privilegeType;

	/* 排序值 */
	private Integer orderView;

	/* 名称空间 当权限是链接时有意义 */
	private String nameSpace;

	/* 父权限 */
	private Privilege parentPrivilege;

	/* 子权限 */
	private Set<Privilege> childPrivilegeSet = new LinkedHashSet<Privilege>();

	/* 权限所在的角色 */
	private Set<Role> roleSet = new HashSet<Role>();

	public Privilege() {
	}

	public Privilege(PrivilegePK privilegePK) {
		this.privilegePK = privilegePK;
	}

	public Privilege(PrivilegePK privilegePK, String name) {
		this.privilegePK = privilegePK;
		this.name = name;
	}

	public void addChildPrivilege(Privilege privilege) {
		childPrivilegeSet.add(privilege);
	}

	@EmbeddedId
	public PrivilegePK getPrivilegePK() {
		return privilegePK;
	}

	@Column(name = "name", length = 20, nullable = false)
	public String getName() {
		return name;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( {
		@JoinColumn(name = "parent_module", referencedColumnName = "module"),
		@JoinColumn(name = "parent_privilege", referencedColumnName = "privilege") }
	)
	public Privilege getParentPrivilege() {
		return parentPrivilege;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentPrivilege", targetEntity = Privilege.class)
	@Cascade({CascadeType.DELETE})
	public Set<Privilege> getChildPrivilegeSet() {
		return childPrivilegeSet;
	}

	@ManyToMany(mappedBy = "privilegeSet", fetch = FetchType.LAZY, targetEntity = Role.class)
	// @Cascade({CascadeType.PERSIST,CascadeType.MERGE})
	// @OnDelete(action=OnDeleteAction.CASCADE)
	public Set<Role> getRoleSet() {
		return roleSet;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "privilege_type", length = 1,  nullable = false)
	public ResourceType getPrivilegeType() {
		return privilegeType;
	}

	@Column(name = "order_view", length = 5)
	public Integer getOrderView() {
		return orderView;
	}

	@Column(name = "name_space", length = 50)
	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public void setOrderView(Integer orderView) {
		this.orderView = orderView;
	}

	public void setPrivilegeType(ResourceType privilegeType) {
		this.privilegeType = privilegeType;
	}

	public void setPrivilegePK(PrivilegePK privilegePK) {
		this.privilegePK = privilegePK;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentPrivilege(Privilege parentPrivilege) {
		this.parentPrivilege = parentPrivilege;
	}

	public void setChildPrivilegeSet(Set<Privilege> childPrivilegeSet) {
		this.childPrivilegeSet = childPrivilegeSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((privilegePK == null) ? 0 : privilegePK.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Privilege other = (Privilege) obj;
		if (privilegePK == null) {
			if (other.privilegePK != null)
				return false;
		} else if (!privilegePK.equals(other.privilegePK))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Privilege [name=" + name + ", privilegePK=" + privilegePK + "]";
	}

	public int compareTo(Privilege o) {
		return this.getOrderView().compareTo(o.getOrderView());
	}

}
