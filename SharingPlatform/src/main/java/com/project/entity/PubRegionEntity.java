package com.project.entity;

import java.io.Serializable;
import java.util.Date;


public class PubRegionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String code;
	//
	private String name;
	//
	private String shortCode;
	//
	private String grade;
	//
	private String map;
	//
	private String coordX;
	//
	private String coordY;
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
	private String type;
	//
	private Integer childs;

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
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	/**
	 * 获取：
	 */
	public String getShortCode() {
		return shortCode;
	}
	/**
	 * 设置：
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * 获取：
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * 设置：
	 */
	public void setMap(String map) {
		this.map = map;
	}
	/**
	 * 获取：
	 */
	public String getMap() {
		return map;
	}
	/**
	 * 设置：
	 */
	public void setCoordX(String coordX) {
		this.coordX = coordX;
	}
	/**
	 * 获取：
	 */
	public String getCoordX() {
		return coordX;
	}
	/**
	 * 设置：
	 */
	public void setCoordY(String coordY) {
		this.coordY = coordY;
	}
	/**
	 * 获取：
	 */
	public String getCoordY() {
		return coordY;
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
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setChilds(Integer childs) {
		this.childs = childs;
	}
	/**
	 * 获取：
	 */
	public Integer getChilds() {
		return childs;
	}
}
