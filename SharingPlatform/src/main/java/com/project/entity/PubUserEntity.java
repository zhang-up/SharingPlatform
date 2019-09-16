package com.project.entity;

import java.io.Serializable;
import java.util.Date;


public class PubUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String account;
	//
	private String name;
	//
	private String password;
	//
	private String userCode;
	//
	private Integer grade;
	//
	private String gender;
	//
	private Date birthday;
	//
	private String identityNum;
	//
	private String phone;
	//
	private String mobile;
	//
	private String email;
	//
	private String position;
	//用户类型
	private String typeCode;
	//
	private Date lastLoginTime;
	//
	private String roleCode;
	//区划编码
	private String regionCode;
	//区划名称
	private String regionName;
	//大（主）机构编码
	private String orgCode;
	//大（主）机构名称
	private String orgName;
	//
	private String orgShortCode;
	//
	private String roleValue;
	//配置管理员级别[0非管理员,1超级或平台级管理员,2部委管理员,3省级管理员,4市级管理员,5县区级管理员,6乡镇街道级管理员,7社区级管理员]
	private Integer isAdmin;
	//
	private String status;
	//
	private String address;
	//标识用户密码是否修改过，未改过为0，改过为1
	private Integer pwdChanged;
	//
	private Date updateTime;
	//
	private String userType;

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
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取：
	 */
	public String getAccount() {
		return account;
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
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**
	 * 获取：
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * 设置：
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	/**
	 * 获取：
	 */
	public Integer getGrade() {
		return grade;
	}
	/**
	 * 设置：
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 获取：
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * 设置：
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 设置：
	 */
	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}
	/**
	 * 获取：
	 */
	public String getIdentityNum() {
		return identityNum;
	}
	/**
	 * 设置：
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * 获取：
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 设置：用户类型
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	/**
	 * 获取：用户类型
	 */
	public String getTypeCode() {
		return typeCode;
	}
	/**
	 * 设置：
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	/**
	 * 获取：
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	/**
	 * 设置：
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	/**
	 * 获取：
	 */
	public String getRoleCode() {
		return roleCode;
	}
	/**
	 * 设置：区划编码
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	/**
	 * 获取：区划编码
	 */
	public String getRegionCode() {
		return regionCode;
	}
	/**
	 * 设置：区划名称
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	/**
	 * 获取：区划名称
	 */
	public String getRegionName() {
		return regionName;
	}
	/**
	 * 设置：大（主）机构编码
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：大（主）机构编码
	 */
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * 设置：大（主）机构名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 获取：大（主）机构名称
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * 设置：
	 */
	public void setOrgShortCode(String orgShortCode) {
		this.orgShortCode = orgShortCode;
	}
	/**
	 * 获取：
	 */
	public String getOrgShortCode() {
		return orgShortCode;
	}
	/**
	 * 设置：
	 */
	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}
	/**
	 * 获取：
	 */
	public String getRoleValue() {
		return roleValue;
	}
	/**
	 * 设置：配置管理员级别[0非管理员,1超级或平台级管理员,2部委管理员,3省级管理员,4市级管理员,5县区级管理员,6乡镇街道级管理员,7社区级管理员]
	 */
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	/**
	 * 获取：配置管理员级别[0非管理员,1超级或平台级管理员,2部委管理员,3省级管理员,4市级管理员,5县区级管理员,6乡镇街道级管理员,7社区级管理员]
	 */
	public Integer getIsAdmin() {
		return isAdmin;
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
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：标识用户密码是否修改过，未改过为0，改过为1
	 */
	public void setPwdChanged(Integer pwdChanged) {
		this.pwdChanged = pwdChanged;
	}
	/**
	 * 获取：标识用户密码是否修改过，未改过为0，改过为1
	 */
	public Integer getPwdChanged() {
		return pwdChanged;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * 获取：
	 */
	public String getUserType() {
		return userType;
	}
}
