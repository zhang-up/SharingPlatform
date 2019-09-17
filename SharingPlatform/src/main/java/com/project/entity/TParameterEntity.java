package com.project.entity;

import java.io.Serializable;


public class TParameterEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//参数ID
	private String parameterId;
	//参数名
	private String parName;
	//参数值
	private String parValue;
	//编码格式：表名|字段名
	private String parCode;
	//备注
	private String remark;

	/**
	 * 设置：参数ID
	 */
	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}
	/**
	 * 获取：参数ID
	 */
	public String getParameterId() {
		return parameterId;
	}
	/**
	 * 设置：参数名
	 */
	public void setParName(String parName) {
		this.parName = parName;
	}
	/**
	 * 获取：参数名
	 */
	public String getParName() {
		return parName;
	}
	/**
	 * 设置：参数值
	 */
	public void setParValue(String parValue) {
		this.parValue = parValue;
	}
	/**
	 * 获取：参数值
	 */
	public String getParValue() {
		return parValue;
	}
	/**
	 * 设置：编码格式：表名|字段名
	 */
	public void setParCode(String parCode) {
		this.parCode = parCode;
	}
	/**
	 * 获取：编码格式：表名|字段名
	 */
	public String getParCode() {
		return parCode;
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
