package com.project.entity;

import java.io.Serializable;
import java.util.Date;


public class RcResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String id;
	//资源版本
	private Integer version;
	//资源名称
	private String resName;
	//资源描述
	private String resDesc;
	//
	private String resType;
	//	关联数据目录ID
	private String cataId;
	//	所属部门ID
	private String orgId;
	//所属行政区划编码
	private String regionCode;
	//所属部门名称
	private String orgName;
	//共享类型
	private Integer shareType;
	//共享条件
	private String shareCondition;
	//内部共享类型   1：无条件共享2：有条件共享3：不予共享
	private Integer innerShareType;
	//内部共享条件
	private String innerShareCondition;
	//开放类型
	private Integer openType;
	//开放条件
	private String openCondition;
	//数据更新周期
	private Integer updateCycle;
	//用户自定义更新周期
	private String customUpdateCycle;
	//	授权方式（1：提供方授权；2：平台授权）
	private Integer authzType;
	//数据是否落地到平台存储（1：落地存储，0：不落地存储）
	private Integer isSavedToPlatform;
	//资源创建方式,1:部门注册,2:目录物化
	private String createMethod;
	//创建者ID
	private String creatorId;
	//创建者姓名
	private String creatorName;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//发布时间
	private Date publishTime;
	//状态，1：草稿；2：注册待审批；3：部门审核通过（待发布）；4：已发布；5：撤销；6：等待撤销状态，到期后将置为撤销状态；-1：删除
	private Integer status;
	//是否允许代理授权,1:允许，0：不允许
	private Integer canProxy;
	//资源过期时间
	private Date expireTime;
	//来源系统id,manaul代表人工填报
	private String fromSystemId;
	//来源系统名称
	private String fromSystemName;

	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：资源版本
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/**
	 * 获取：资源版本
	 */
	public Integer getVersion() {
		return version;
	}
	/**
	 * 设置：资源名称
	 */
	public void setResName(String resName) {
		this.resName = resName;
	}
	/**
	 * 获取：资源名称
	 */
	public String getResName() {
		return resName;
	}
	/**
	 * 设置：资源描述
	 */
	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}
	/**
	 * 获取：资源描述
	 */
	public String getResDesc() {
		return resDesc;
	}
	/**
	 * 设置：
	 */
	public void setResType(String resType) {
		this.resType = resType;
	}
	/**
	 * 获取：
	 */
	public String getResType() {
		return resType;
	}
	/**
	 * 设置：	关联数据目录ID
	 */
	public void setCataId(String cataId) {
		this.cataId = cataId;
	}
	/**
	 * 获取：	关联数据目录ID
	 */
	public String getCataId() {
		return cataId;
	}
	/**
	 * 设置：	所属部门ID
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：	所属部门ID
	 */
	public String getOrgId() {
		return orgId;
	}
	/**
	 * 设置：所属行政区划编码
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	/**
	 * 获取：所属行政区划编码
	 */
	public String getRegionCode() {
		return regionCode;
	}
	/**
	 * 设置：所属部门名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 获取：所属部门名称
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * 设置：共享类型
	 */
	public void setShareType(Integer shareType) {
		this.shareType = shareType;
	}
	/**
	 * 获取：共享类型
	 */
	public Integer getShareType() {
		return shareType;
	}
	/**
	 * 设置：共享条件
	 */
	public void setShareCondition(String shareCondition) {
		this.shareCondition = shareCondition;
	}
	/**
	 * 获取：共享条件
	 */
	public String getShareCondition() {
		return shareCondition;
	}
	/**
	 * 设置：内部共享类型   1：无条件共享2：有条件共享3：不予共享
	 */
	public void setInnerShareType(Integer innerShareType) {
		this.innerShareType = innerShareType;
	}
	/**
	 * 获取：内部共享类型   1：无条件共享2：有条件共享3：不予共享
	 */
	public Integer getInnerShareType() {
		return innerShareType;
	}
	/**
	 * 设置：内部共享条件
	 */
	public void setInnerShareCondition(String innerShareCondition) {
		this.innerShareCondition = innerShareCondition;
	}
	/**
	 * 获取：内部共享条件
	 */
	public String getInnerShareCondition() {
		return innerShareCondition;
	}
	/**
	 * 设置：开放类型
	 */
	public void setOpenType(Integer openType) {
		this.openType = openType;
	}
	/**
	 * 获取：开放类型
	 */
	public Integer getOpenType() {
		return openType;
	}
	/**
	 * 设置：开放条件
	 */
	public void setOpenCondition(String openCondition) {
		this.openCondition = openCondition;
	}
	/**
	 * 获取：开放条件
	 */
	public String getOpenCondition() {
		return openCondition;
	}
	/**
	 * 设置：数据更新周期
	 */
	public void setUpdateCycle(Integer updateCycle) {
		this.updateCycle = updateCycle;
	}
	/**
	 * 获取：数据更新周期
	 */
	public Integer getUpdateCycle() {
		return updateCycle;
	}
	/**
	 * 设置：用户自定义更新周期
	 */
	public void setCustomUpdateCycle(String customUpdateCycle) {
		this.customUpdateCycle = customUpdateCycle;
	}
	/**
	 * 获取：用户自定义更新周期
	 */
	public String getCustomUpdateCycle() {
		return customUpdateCycle;
	}
	/**
	 * 设置：	授权方式（1：提供方授权；2：平台授权）
	 */
	public void setAuthzType(Integer authzType) {
		this.authzType = authzType;
	}
	/**
	 * 获取：	授权方式（1：提供方授权；2：平台授权）
	 */
	public Integer getAuthzType() {
		return authzType;
	}
	/**
	 * 设置：数据是否落地到平台存储（1：落地存储，0：不落地存储）
	 */
	public void setIsSavedToPlatform(Integer isSavedToPlatform) {
		this.isSavedToPlatform = isSavedToPlatform;
	}
	/**
	 * 获取：数据是否落地到平台存储（1：落地存储，0：不落地存储）
	 */
	public Integer getIsSavedToPlatform() {
		return isSavedToPlatform;
	}
	/**
	 * 设置：资源创建方式,1:部门注册,2:目录物化
	 */
	public void setCreateMethod(String createMethod) {
		this.createMethod = createMethod;
	}
	/**
	 * 获取：资源创建方式,1:部门注册,2:目录物化
	 */
	public String getCreateMethod() {
		return createMethod;
	}
	/**
	 * 设置：创建者ID
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * 获取：创建者ID
	 */
	public String getCreatorId() {
		return creatorId;
	}
	/**
	 * 设置：创建者姓名
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	/**
	 * 获取：创建者姓名
	 */
	public String getCreatorName() {
		return creatorName;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：发布时间
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getPublishTime() {
		return publishTime;
	}
	/**
	 * 设置：状态，1：草稿；2：注册待审批；3：部门审核通过（待发布）；4：已发布；5：撤销；6：等待撤销状态，到期后将置为撤销状态；-1：删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态，1：草稿；2：注册待审批；3：部门审核通过（待发布）；4：已发布；5：撤销；6：等待撤销状态，到期后将置为撤销状态；-1：删除
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：是否允许代理授权,1:允许，0：不允许
	 */
	public void setCanProxy(Integer canProxy) {
		this.canProxy = canProxy;
	}
	/**
	 * 获取：是否允许代理授权,1:允许，0：不允许
	 */
	public Integer getCanProxy() {
		return canProxy;
	}
	/**
	 * 设置：资源过期时间
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	/**
	 * 获取：资源过期时间
	 */
	public Date getExpireTime() {
		return expireTime;
	}
	/**
	 * 设置：来源系统id,manaul代表人工填报
	 */
	public void setFromSystemId(String fromSystemId) {
		this.fromSystemId = fromSystemId;
	}
	/**
	 * 获取：来源系统id,manaul代表人工填报
	 */
	public String getFromSystemId() {
		return fromSystemId;
	}
	/**
	 * 设置：来源系统名称
	 */
	public void setFromSystemName(String fromSystemName) {
		this.fromSystemName = fromSystemName;
	}
	/**
	 * 获取：来源系统名称
	 */
	public String getFromSystemName() {
		return fromSystemName;
	}
}
