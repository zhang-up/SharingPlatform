package com.project.entity;

import java.io.Serializable;


public class TDemandResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//推荐资源ID
	private String dResId;
	//需求ID
	private String demandId;
	//资源ID
	private String resourceId;
	//当前的状态：0，没选择，1，被选择
	private String state;

	/**
	 * 设置：推荐资源ID
	 */
	public void setDResId(String dResId) {
		this.dResId = dResId;
	}
	/**
	 * 获取：推荐资源ID
	 */
	public String getDResId() {
		return dResId;
	}
	/**
	 * 设置：需求ID
	 */
	public void setDemandId(String demandId) {
		this.demandId = demandId;
	}
	/**
	 * 获取：需求ID
	 */
	public String getDemandId() {
		return demandId;
	}
	/**
	 * 设置：资源ID
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	/**
	 * 获取：资源ID
	 */
	public String getResourceId() {
		return resourceId;
	}
	/**
	 * 设置：当前的状态：0，没选择，1，被选择
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：当前的状态：0，没选择，1，被选择
	 */
	public String getState() {
		return state;
	}
}
