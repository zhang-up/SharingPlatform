package com.project.param;

import java.io.Serializable;


public class PubOrganParam implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String code;
	//
	private String name;
	
	private String parentCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	
}
