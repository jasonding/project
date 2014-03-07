package com.project.domain.game;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "game_user")
public class GameUser {
	private Long id;
	private String name;
	private String password;
	private Date createTime;
	private Date updateTime;
	private Date lastLoginTime;

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


	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String  name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
