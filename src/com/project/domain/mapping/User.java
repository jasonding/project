package com.project.domain.mapping;

import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.project.enumeration.Gender;

@Entity
@Table(name = "user")
public class User {
	private Long id;
	private String name;
	private String password;
	private Gender gender;
	private Date createTime;
	private Date updateTime;
	private Date lastLoginTime;
	private Set<Role> roleSet = new HashSet<Role>();
	
	/**
	 * 给用户添加角色
	 * @param role
	 */
	public void addRole(Role role) {
		roleSet.add(role);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", length = 20)
	public Long getId() {
		return id;
	}

	@Column(name = "user_name", length = 20, nullable = false, unique = true)
	public String getName() {
		return name;
	}

	@Column(name = "password", length = 64, nullable = false)
	public String getPassword() {
		return password;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name="gender",length=1)
	public Gender getGender() {
		return gender;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_time")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	@ManyToMany(fetch=FetchType.EAGER,targetEntity=Role.class)
	@JoinTable(name="user_role",
			   joinColumns=@JoinColumn(name="user_id"),
			   inverseJoinColumns=@JoinColumn(name="role_id")
	)
	@Cascade({CascadeType.REFRESH})
	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String  name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
