package com.project.entity;

import java.io.Serializable;
import java.util.Date;


public class PubOrganTreeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String orgCode;
	//
	private String parentCode;
	//
	private String viewCode;
	//
	private String isLeaf;
	//
	private Integer sortOrder;
	//
	private String creator;
	//
	private Date createTime;
	//
	private String remark;
	//
	private String status;
	//
	private String pathParentCode;

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
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：
	 */
	public String getOrgCode() {
		return orgCode;
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
	public void setViewCode(String viewCode) {
		this.viewCode = viewCode;
	}
	/**
	 * 获取：
	 */
	public String getViewCode() {
		return viewCode;
	}
	/**
	 * 设置：
	 */
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	/**
	 * 获取：
	 */
	public String getIsLeaf() {
		return isLeaf;
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
	public void setPathParentCode(String pathParentCode) {
		this.pathParentCode = pathParentCode;
	}
	/**
	 * 获取：
	 */
	public String getPathParentCode() {
		return pathParentCode;
	}
}
