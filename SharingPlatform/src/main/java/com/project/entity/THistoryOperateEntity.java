package com.project.entity;

import java.io.Serializable;


public class THistoryOperateEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//操作ID
	private String operateId;
	//历史ID
	private String historyId;
	//操作人
	private String operator;
	//操作时间
	private String operateTime;
	//需求当前的状态：00草稿，01已提交，02已处理完成，03已撤销，04已删除
	private String state;
	//备注
	private String remark;
	//附件地址
	private String fileAdd;

	/**
	 * 设置：操作ID
	 */
	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}
	/**
	 * 获取：操作ID
	 */
	public String getOperateId() {
		return operateId;
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
	 * 设置：操作人
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * 获取：操作人
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * 设置：操作时间
	 */
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	/**
	 * 获取：操作时间
	 */
	public String getOperateTime() {
		return operateTime;
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
	/**
	 * 设置：附件地址
	 */
	public void setFileAdd(String fileAdd) {
		this.fileAdd = fileAdd;
	}
	/**
	 * 获取：附件地址
	 */
	public String getFileAdd() {
		return fileAdd;
	}
}
