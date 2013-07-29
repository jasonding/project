package com.project.domain.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	private String authority;
	
	private String authorityName;
	
	public Authority(String authority) {
		this(authority,authority);
	}

	public Authority(String authority, String authorityName) {
		this.authority = authority;
		this.authorityName = authorityName;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	@Override
	public String toString() {
		return "Authority [authorityName=" + authorityName + "]";
	}
}
