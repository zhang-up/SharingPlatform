package com.project.entity;

import java.io.Serializable;


public class TImportDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//需求ID
	private String importDetailId;
	//需求ID
	private String importId;
	//需求ID
	private String demandId;
	//提供单位
	private String provideDep;
	//提供单位名
	private String provideDepName;
	//期望提供方式:1服务接口，2库表，3文件
	private String accessMode;
	//期望提供方式名
	private String accessModeName;
	//期望共享服务方式:1查询，2核验，3批量交换
	private String serveMode;
	//期望共享服务方式名
	private String serveModeName;
	//期望更新频率:01实时，02小时，03天，04周，05半月，06月，07季，08半年，09年
	private String frequency;
	//期望更新频率名
	private String frequencyName;
	//行数
	private int rowsNums;

	/**
	 * 设置：需求ID
	 */
	public void setImportDetailId(String importDetailId) {
		this.importDetailId = importDetailId;
	}
	/**
	 * 获取：需求ID
	 */
	public String getImportDetailId() {
		return importDetailId;
	}
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
	 * 设置：提供单位
	 */
	public void setProvideDep(String provideDep) {
		this.provideDep = provideDep;
	}
	/**
	 * 获取：提供单位
	 */
	public String getProvideDep() {
		return provideDep;
	}
	/**
	 * 设置：提供单位名
	 */
	public void setProvideDepName(String provideDepName) {
		this.provideDepName = provideDepName;
	}
	/**
	 * 获取：提供单位名
	 */
	public String getProvideDepName() {
		return provideDepName;
	}
	/**
	 * 设置：期望提供方式:1服务接口，2库表，3文件
	 */
	public void setAccessMode(String accessMode) {
		this.accessMode = accessMode;
	}
	/**
	 * 获取：期望提供方式:1服务接口，2库表，3文件
	 */
	public String getAccessMode() {
		return accessMode;
	}
	/**
	 * 设置：期望提供方式名
	 */
	public void setAccessModeName(String accessModeName) {
		this.accessModeName = accessModeName;
	}
	/**
	 * 获取：期望提供方式名
	 */
	public String getAccessModeName() {
		return accessModeName;
	}
	/**
	 * 设置：期望共享服务方式:1查询，2核验，3批量交换
	 */
	public void setServeMode(String serveMode) {
		this.serveMode = serveMode;
	}
	/**
	 * 获取：期望共享服务方式:1查询，2核验，3批量交换
	 */
	public String getServeMode() {
		return serveMode;
	}
	/**
	 * 设置：期望共享服务方式名
	 */
	public void setServeModeName(String serveModeName) {
		this.serveModeName = serveModeName;
	}
	/**
	 * 获取：期望共享服务方式名
	 */
	public String getServeModeName() {
		return serveModeName;
	}
	/**
	 * 设置：期望更新频率:01实时，02小时，03天，04周，05半月，06月，07季，08半年，09年
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	/**
	 * 获取：期望更新频率:01实时，02小时，03天，04周，05半月，06月，07季，08半年，09年
	 */
	public String getFrequency() {
		return frequency;
	}
	/**
	 * 设置：期望更新频率名
	 */
	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
	}
	/**
	 * 获取：期望更新频率名
	 */
	public String getFrequencyName() {
		return frequencyName;
	}
	public int getRowsNums() {
		return rowsNums;
	}
	public void setRowsNums(int rowsNums) {
		this.rowsNums = rowsNums;
	}
	
	
	
}
