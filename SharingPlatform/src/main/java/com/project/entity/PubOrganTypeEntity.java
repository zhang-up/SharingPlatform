package com.project.entity;

import java.io.Serializable;
import java.util.Date;


public class PubOrganTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String code;
	//
	private String name;
	//
	private String parentCode;
	//
	private Integer sortOrder;
	//
	private String creator;
	//
	private Date createTime;
	//
	private String lastEditor;
	//
	private Date lastTime;
	//
	private String remark;
	//
	private String status;
	//
	private String isDefault;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	/**
	 * 获取：
	 */
	public String getParentCode() {
		return parentCode;
	}
	/**
	 * 设置：
	 */
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	/**
	 * 获取：
	 */
	public Integer getSortOrder() {
		return sortOrder;
	}
	/**
	 * 设置：
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * 获取：
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setLastEditor(String lastEditor) {
		this.lastEditor = lastEditor;
	}
	/**
	 * 获取：
	 */
	public String getLastEditor() {
		return lastEditor;
	}
	/**
	 * 设置：
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	/**
	 * 获取：
	 */
	public Date getLastTime() {
		return lastTime;
	}
	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * 获取：
	 */
	public String getIsDefault() {
		return isDefault;
	}
}
