/**
 * 
 */
package com.project.enumeration;

/**
 * @author dingjs
 */
public enum Gender {
	BOTH("BOTH", "全部"), 
	MAN("MAN", "男"), 
	WOMAN("WOMAN", "女");

	private String value;
	private String name;

	private Gender(String value, String name) {
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
