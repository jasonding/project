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
import javax.persistence.Transient;


/**
 * @author dingjs
 */
@Entity
@Table(name="role")
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Set<Privilege> privilegeSet = new HashSet<Privilege>();
	private Set<User> userSet = new HashSet<User>();
	
	/**
	 * 给角色添加一个权限
	 * @param privilege
	 */
	public void addPrivilege(Privilege privilege) {
		privilegeSet.add(privilege);
	}

	public Role(){}
	
	public Role(Long id) {
		super();
		this.id = id;
	}

	public Role(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
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

	@ManyToMany(mappedBy="roleSet",fetch=FetchType.LAZY,targetEntity=User.class)
	//@Cascade({CascadeType.REFRESH})
	public Set<User> getUserSet() {
		return userSet;
	}

	@ManyToMany(fetch=FetchType.EAGER,targetEntity=Privilege.class)
	@JoinTable(name="role_privilege",
			   joinColumns=@JoinColumn(name="role_id"),
			   inverseJoinColumns=@JoinColumn(name="privilege_id")
	)
	//@Cascade({CascadeType.REFRESH})
	public Set<Privilege> getPrivilegeSet() {
		return privilegeSet;
	}

	public void setPrivilegeSet(Set<Privilege> privilegeSet) {
		this.privilegeSet = privilegeSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
	@Transient
	public String getAuthority() {
		return "AUTHORITY_" + getId();
	}
}
