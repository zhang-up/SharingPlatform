package com.project.info;

import java.io.Serializable;
import java.util.Date;


public class RcResourceInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String id;
	//资源名称
	private String resName;

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
}
