package com.project.enumeration;

/**
 * 资源是否显示在菜单中状态
 * @author dingjs
 * 
 */
public enum DisplayType {
	HIDDEN("HIDDEN","不显示"),

	SHOW("SHOW", "显示");

	private String value;
	private String name;

	private DisplayType(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
