package com.project.info;

import java.io.Serializable;


public class TDemandStatisticInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//需求单位
	private String demandDep;
	private String demandDepName;
	//提供单位
	private String provideDep;
	private String provideDepName;
	
	//应提供
	private int shouldProNums;
	//已提供
	private int alreadyProNums;
	//未提供
	private int notProNums;
	//已申请
	private int appliedForNums;
	//已初审
	private int firstTrialNums;
	//已回退
	private int regressionNums;
	//已确认
	private int confirmedNums;
	//已驳回
	private int rejectNums;
	//已撤销
	private int rescindedNums;
	//已共享
	private int sharedNums;
	//未完成
	private int noFinishNums;
	
	public String get(String nameId) {
		if("provideDepName".equals(nameId)){
			return this.provideDepName;
		}else if("shouldProNums".equals(nameId)){
			return String.valueOf(this.shouldProNums);
		}else if("alreadyProNums".equals(nameId)){
			return String.valueOf(this.alreadyProNums);
		}else if("notProNums".equals(nameId)){
			return String.valueOf(this.notProNums);
		}else if("appliedForNums".equals(nameId)){
			return String.valueOf(this.appliedForNums);
		}else if("firstTrialNums".equals(nameId)){
			return String.valueOf(this.firstTrialNums);
		}else if("confirmedNums".equals(nameId)){
			return String.valueOf(this.confirmedNums);
		}else if("rescindedNums".equals(nameId)){
			return String.valueOf(this.rescindedNums);
		}else if("rejectNums".equals(nameId)){
			return String.valueOf(this.rejectNums);
		}else if("regressionNums".equals(nameId)){
			return String.valueOf(this.regressionNums);
		}else if("sharedNums".equals(nameId)){
			return String.valueOf(this.sharedNums);
		}else if("noFinishNums".equals(nameId)){
			return String.valueOf(this.noFinishNums);
		}
		
		return "";
	}
	
	public String getDemandDep() {
		return demandDep;
	}
	public void setDemandDep(String demandDep) {
		this.demandDep = demandDep;
	}
	public String getDemandDepName() {
		return demandDepName;
	}
	public void setDemandDepName(String demandDepName) {
		this.demandDepName = demandDepName;
	}
	public String getProvideDep() {
		return provideDep;
	}
	public void setProvideDep(String provideDep) {
		this.provideDep = provideDep;
	}
	public String getProvideDepName() {
		return provideDepName;
	}
	public void setProvideDepName(String provideDepName) {
		this.provideDepName = provideDepName;
	}
	public int getShouldProNums() {
		return shouldProNums;
	}
	public void setShouldProNums(int shouldProNums) {
		this.shouldProNums = shouldProNums;
	}
	public int getAlreadyProNums() {
		return alreadyProNums;
	}
	public void setAlreadyProNums(int alreadyProNums) {
		this.alreadyProNums = alreadyProNums;
	}
	public int getNotProNums() {
		return notProNums;
	}
	public void setNotProNums(int notProNums) {
		this.notProNums = notProNums;
	}
	public int getAppliedForNums() {
		return appliedForNums;
	}
	public void setAppliedForNums(int appliedForNums) {
		this.appliedForNums = appliedForNums;
	}
	public int getFirstTrialNums() {
		return firstTrialNums;
	}
	public void setFirstTrialNums(int firstTrialNums) {
		this.firstTrialNums = firstTrialNums;
	}
	public int getConfirmedNums() {
		return confirmedNums;
	}
	public void setConfirmedNums(int confirmedNums) {
		this.confirmedNums = confirmedNums;
	}
	public int getRescindedNums() {
		return rescindedNums;
	}
	public void setRescindedNums(int rescindedNums) {
		this.rescindedNums = rescindedNums;
	}

	public int getRejectNums() {
		return rejectNums;
	}

	public void setRejectNums(int rejectNums) {
		this.rejectNums = rejectNums;
	}

	public int getRegressionNums() {
		return regressionNums;
	}

	public void setRegressionNums(int regressionNums) {
		this.regressionNums = regressionNums;
	}

	public int getSharedNums() {
		return sharedNums;
	}

	public void setSharedNums(int sharedNums) {
		this.sharedNums = sharedNums;
	}

	public int getNoFinishNums() {
		return noFinishNums;
	}

	public void setNoFinishNums(int noFinishNums) {
		this.noFinishNums = noFinishNums;
	}
	
}
