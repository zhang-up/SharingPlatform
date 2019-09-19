package com.project.dao;

import java.util.Map;

import com.project.entity.TDemandOperateEntity;
import com.project.info.DockTrialInfo;


public interface DockTrialDao {
	DockTrialInfo trialInfoList(Map<String, Object> map);

	void changTrial(Map<String, Object> params);
	
	void insertAgreeTrial(Map<String, Object> params);
	
	DockTrialInfo findTrialPeople(Map<String, Object> map);
	
	DockTrialInfo findProvidePeople(Map<String, Object> map);
	
	TDemandOperateEntity findTdOperate(Map<String, Object> map);
	
	void importD(Map<String, Object> map);
	
	

}
