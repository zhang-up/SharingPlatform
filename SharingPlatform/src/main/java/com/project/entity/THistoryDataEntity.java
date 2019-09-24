package com.project.entity;

import java.io.Serializable;


public class THistoryDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//历史ID
	private String historyId;
	//提供单位
	private String provideDep;
	//资源名
	private String hisName;
	//数据项
	private String hisDetail;
	//数据周期
	private String period;
	//时间格式 yyyy-MM-dd hh:mm:ss
	private String saveTime;
	//创建人
	private String creater;
	//需求当前的状态：00草稿，01已提交，02已处理完成，03已撤销，04已删除
	private String stateT;
	
	//需求当前的状态：00草稿，01已提交，02已处理完成，03已撤销，04已删除
	private String state;
	//备注
	private String remark;

	/**
	 * 设置：历史ID
	 */
	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}
	/**
	 * 获取：历史ID
	 */
	public String getHistoryId() {
		return historyId;
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
	public void setHisName(String hisName) {
		this.hisName = hisName;
	}
	/**
	 * 获取：资源名
	 */
	public String getHisName() {
		return hisName;
	}
	/**
	 * 设置：数据项
	 */
	public void setHisDetail(String hisDetail) {
		this.hisDetail = hisDetail;
	}
	/**
	 * 获取：数据项
	 */
	public String getHisDetail() {
		return hisDetail;
	}
	/**
	 * 设置：数据周期
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
	/**
	 * 获取：数据周期
	 */
	public String getPeriod() {
		return period;
	}
	/**
	 * 设置：时间格式 yyyy-MM-dd hh:mm:ss
	 */
	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}
	/**
	 * 获取：时间格式 yyyy-MM-dd hh:mm:ss
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
	 * 设置：需求当前的状态：00草稿，01已提交，02已处理完成，03已撤销，04已删除
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：需求当前的状态：00草稿，01已提交，02已处理完成，03已撤销，04已删除
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
	
	
	
	public String getStateT() {
		return stateT;
	}
	public void setStateT(String stateT) {
		this.stateT = stateT;
	}
	@Override
	public String toString() {
		return "THistoryDataEntity [historyId=" + historyId + ", provideDep=" + provideDep + ", hisName=" + hisName
				+ ", hisDetail=" + hisDetail + ", period=" + period + ", saveTime=" + saveTime + ", creater=" + creater
				+ ", state=" + state + ", remark=" + remark + "]";
	}
	
}
