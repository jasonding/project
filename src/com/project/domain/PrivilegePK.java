package com.project.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PrivilegePK implements Serializable{
	private static final long serialVersionUID = 1L;

	/* 权限模块 */
	private String module;
	
	/* 权限类型 */
	private String privilege;

	
	public PrivilegePK(String module) {
		this.module = module;
	}

	public PrivilegePK(String module, String privilege) {
		this.module = module;
		this.privilege = privilege;
	}

	public PrivilegePK() {}
	
	@Column(name="module",length=50)
	public String getModule() {
		return module;
	}

	@Column(name="privilege",length=50)
	public String getPrivilege() {
		return privilege;
	}
	
	public void setModule(String module) {
		this.module = module;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result
				+ ((privilege == null) ? 0 : privilege.hashCode());
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
		PrivilegePK other = (PrivilegePK) obj;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (privilege == null) {
			if (other.privilege != null)
				return false;
		} else if (!privilege.equals(other.privilege))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PrivilegePK [module=" + module + ", privilege=" + privilege + "]";
	}
}
