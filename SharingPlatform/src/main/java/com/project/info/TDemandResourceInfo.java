package com.project.info;

import java.io.Serializable;


public class TDemandResourceInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String id;
	//资源名称
	private String resName;
	private String state;

	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：资源名称
	 */
	public void setResName(String resName) {
		this.resName = resName;
	}
	/**
	 * 获取：资源名称
	 */
	public String getResName() {
		return resName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
