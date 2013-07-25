package com.project.enumeration;

/**
 * 资源类型
 * @author dingjs
 * 
 */
public enum ResourceType {
	DEFAULT("DEFAULT", "默认"),
	
	MENU("MENU","菜单"), 
	
	LINK("LINK", "链接"), 
	
	BUTTON("BUTTON", "按钮");

	private String value;
	private String name;

	private ResourceType(String value, String name) {
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
