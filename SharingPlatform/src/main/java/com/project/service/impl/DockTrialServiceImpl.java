package com.project.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.DockTrialDao;
import com.project.info.DockTrialInfo;
import com.project.service.DockTrialService;

@Service
public class DockTrialServiceImpl implements DockTrialService{
	@Autowired
	private DockTrialDao dockTrialDao;

	@Override
	public DockTrialInfo trialInfoList(Map<String, Object> map) {
		return dockTrialDao.trialInfoList(map);
	}
	

	
}
