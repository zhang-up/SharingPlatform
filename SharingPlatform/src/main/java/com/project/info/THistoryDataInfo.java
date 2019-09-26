package com.project.info;

import java.io.Serializable;

import com.project.utils.CTools;
import com.project.utils.StringUtil;


public class THistoryDataInfo implements Serializable {
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
	
	//数据周期--前	
	private String beforeT;
	
	//数据周期--后
	private String lastT;
		
	//时间格式 yyyy-MM-dd hh:mm:ss,data表的时间
	private String saveTime;
	//创建人,data表的人
	private String creater;
	private String createrNumber;
	private String mobile;
	//operate表的时间
	private String operateTime;
	//operate 表的人
	private String operatePeo;	
	//需求当前的状态：00草稿，01已提交，02已处理完成，03已撤销，04已删除--数字
	private String stateT;	
	//需求当前的状态：00草稿，01已提交，02已处理完成，03已撤销，04已删除
	private String state;
	//备注
	private String remark;
	
	
	//处理结果 
	private String result;
	
	//处理结果 状态  1 接入 ,2  不接入
	private String resultRes;
	
	//处理说明  
	private String resultUse;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResultUse() {
		return resultUse;
	}
	public void setResultUse(String resultUse) {
		this.resultUse = resultUse;
	}
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
	public String getBeforeT() {
		return beforeT;
	}
	public void setBeforeT(String beforeT) {
		this.beforeT = beforeT;
	}
	public String getLastT() {
		return lastT;
	}
	public void setLastT(String lastT) {
		this.lastT = lastT;
	}
	public String getResultRes() {
		return resultRes;
	}
	public void setResultRes(String resultRes) {
		this.resultRes = resultRes;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperatePeo() {
		return operatePeo;
	}
	public void setOperatePeo(String operatePeo) {
		this.operatePeo = operatePeo;
	}
	public String getCreaterNumber() {
		return createrNumber;
	}
	public void setCreaterNumber(String createrNumber) {
		this.createrNumber = createrNumber;
	}
	public String getMobile() {
		if(StringUtil.isNull(mobile)){
			return mobile;
		}else{
			return CTools.decrypt(mobile);
		}
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	
	
	
}
