package com.project.entity;

import java.io.Serializable;


public class TDemandEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//需求ID
	private String demandId;
	//需求单位
	private String demandDep;
	//提供单位
	private String provideDep;
	//资源名
	private String demandName;
	//关键字用于匹配资源，支持多个关键字（不超过5个），要求用中文逗号‘,’隔开
	private String keyWord;
	//数据项
	private String demandDetail;
	//期望提供方式:1服务接口，2库表，3文件
	private String accessMode;
	//期望共享服务方式:1查询，2核验，3批量交换
	private String serveMode;
	//期望更新频率:01实时，02小时，03天，04周，05半月，06月，07季，08半年，09年
	private String frequency;
	//用途
	private String demandUse;
	//保存时间:时间格式 yyyy-MM-dd hh:mm:ss
	private String saveTime;
	//创建人
	private String creater;
	//需求当前的状态：00草稿，01已提交，02初审通过，03初审回退，04提供单位通过，05提供单位驳回，06已完成，07已撤销，08已删除
	private String state;
	//备注
	private String remark;

	public void updateValue(TDemandEntity bs) {
    	this.setDemandId(bs.getDemandId());
    	this.setDemandDep(bs.getDemandDep());
    	this.setProvideDep(bs.getProvideDep());
    	this.setDemandName(bs.getDemandName());
    	this.setKeyWord(bs.getKeyWord());
    	this.setDemandDetail(bs.getDemandDetail());
    	this.setAccessMode(bs.getAccessMode());
    	this.setServeMode(bs.getServeMode());
    	this.setFrequency(bs.getFrequency());
    	this.setDemandUse(bs.getDemandUse());
    	this.setSaveTime(bs.getSaveTime());
    	this.setCreater(bs.getCreater());
    	this.setState(bs.getState());
    	this.setRemark(bs.getRemark());
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
	 * 设置：需求单位
	 */
	public void setDemandDep(String demandDep) {
		this.demandDep = demandDep;
	}
	/**
	 * 获取：需求单位
	 */
	public String getDemandDep() {
		return demandDep;
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
	 * 设置：资源名
	 */
	public void setDemandName(String demandName) {
		this.demandName = demandName;
	}
	/**
	 * 获取：资源名
	 */
	public String getDemandName() {
		return demandName;
	}
	/**
	 * 设置：关键字用于匹配资源，支持多个关键字（不超过5个），要求用中文逗号‘,’隔开
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	/**
	 * 获取：关键字用于匹配资源，支持多个关键字（不超过5个），要求用中文逗号‘,’隔开
	 */
	public String getKeyWord() {
		return keyWord;
	}
	/**
	 * 设置：数据项
	 */
	public void setDemandDetail(String demandDetail) {
		this.demandDetail = demandDetail;
	}
	/**
	 * 获取：数据项
	 */
	public String getDemandDetail() {
		return demandDetail;
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
	 * 设置：用途
	 */
	public void setDemandUse(String demandUse) {
		this.demandUse = demandUse;
	}
	/**
	 * 获取：用途
	 */
	public String getDemandUse() {
		return demandUse;
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
	/**
	 * 设置：需求当前的状态：00草稿，01已提交，02初审通过，03初审回退，04提供单位通过，05提供单位驳回，06已完成，07已撤销，08已删除
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：需求当前的状态：00草稿，01已提交，02初审通过，03初审回退，04提供单位通过，05提供单位驳回，06已完成，07已撤销，08已删除
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
}
