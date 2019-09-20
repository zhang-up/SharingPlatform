package com.project.entity;

import java.io.Serializable;
import java.util.Date;


public class TImportEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//需求ID
	private String importId;
	//保存时间:时间格式 yyyy-MM-dd hh:mm:ss
	private String saveTime;
	//创建人
	private String creater;

	/**
	 * 设置：需求ID
	 */
	public void setImportId(String importId) {
		this.importId = importId;
	}
	/**
	 * 获取：需求ID
	 */
	public String getImportId() {
		return importId;
	}
	/**
	 * 设置：保存时间:时间格式 yyyy-MM-dd hh:mm:ss
	 */
	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}
	/**
	 * 获取：保存时间:时间格式 yyyy-MM-dd hh:mm:ss
	 */
	public String getSaveTime() {
		return saveTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreater(String creater) {
		this.creater = creater;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreater() {
		return creater;
	}
}
