package com.project.entity;

import java.io.Serializable;


public class TDemandOperateEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//操作ID
	private String operateId;
	//需求ID
	private String demandId;
	//操作人
	private String operator;
	//操作时间
	private String operateTime;
	//需求当前的状态：00草稿，01已提交，02初审通过，03初审回退，04提供单位通过，05提供单位驳回，06已完成，07已撤销，08已删除
	private String state;
	//0该状态没有结果
//            对接人审核结果为：1同意，2回退
//            提供方确认结果为：1通过，2驳回
	private String operateRes;
	//0无原因
       /*     对接人：
            驳回：1需求不清，2需求杂糅,3需求重复,4已共享,5其他
            提供方：
            驳回：1需求不清，2需求杂糅,3需求重复,4数据涉密,5无此数据,6其他
            通过：1已共享，2待接入*/
	private String cause;
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
	 * 设置：0该状态没有结果
            对接人审核结果为：1同意，2回退
            提供方确认结果为：1通过，2驳回
	 */
	public void setOperateRes(String operateRes) {
		this.operateRes = operateRes;
	}
	/**
	 * 获取：0该状态没有结果
            对接人审核结果为：1同意，2回退
            提供方确认结果为：1通过，2驳回
	 */
	public String getOperateRes() {
		return operateRes;
	}
	/**
	 * 设置：0无原因
            对接人：
            驳回：1需求不清，2需求杂糅,3需求重复,4已共享,5其他
            提供方：
            驳回：1需求不清，2需求杂糅,3需求重复,4数据涉密,5无此数据,6其他
            通过：1已共享，2待接入
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}
	/**
	 * 获取：0无原因
            对接人：
            驳回：1需求不清，2需求杂糅,3需求重复,4已共享,5其他
            提供方：
            驳回：1需求不清，2需求杂糅,3需求重复,4数据涉密,5无此数据,6其他
            通过：1已共享，2待接入
	 */
	public String getCause() {
		return cause;
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
