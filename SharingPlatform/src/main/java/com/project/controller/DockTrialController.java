package com.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
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
	@RequestMapping("/info/{demandId}")
	public DockTrialInfo info(@PathVariable("demandId") String demandId){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("demandDep", demandId);
		System.out.println("=========================================="+demandId);
		return dockTrialService.trialInfoList(map);
	}
}
