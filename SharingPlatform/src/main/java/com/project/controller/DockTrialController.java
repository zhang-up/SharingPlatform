package com.project.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.info.DockTrialInfo;
import com.project.service.DockTrialService;

@RestController
@RequestMapping("/dock")
public class DockTrialController {
	@Autowired
	private DockTrialService dockTrialService;
	
	@RequestMapping("/list")
	public DockTrialInfo trialInfoList(Map<String, Object> map) {
		map.put("demandDep", "13");
		return dockTrialService.trialInfoList(map);
	}

}
