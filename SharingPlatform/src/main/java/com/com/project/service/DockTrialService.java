package com.com.project.service;

import java.util.Map;

import com.project.info.DockTrialInfo;

public interface DockTrialService {
	DockTrialInfo trialInfoList(Map<String, Object> map);
	void dealTrial(Map<String, Object> map);
	DockTrialInfo findTrialPeople(Map<String, Object> map);
	String dealProvide(Map<String, Object> map);//提供者审核
	DockTrialInfo finishVerify(Map<String, Object> map);
	void finishDleal(Map<String, Object> map);
	void importD(Map<String, Object> map);
	
}
