package com.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.DockTrialDao;
import com.project.info.DockTrialInfo;
import com.project.service.DockTrialService;
import com.project.utils.DateUtil;

@Service
public class DockTrialServiceImpl implements DockTrialService{
	@Autowired
	private DockTrialDao dockTrialDao;

	@Override
	public DockTrialInfo trialInfoList(Map<String, Object> map) {
		return dockTrialDao.trialInfoList(map);
	}

	@Override
	public void dealTrial(Map<String, Object> params) {		
		String token = params.get("token") == null ? "" : params.get("token").toString();//用户ID
		String demandUse = params.get("demandUse") == null ? "" : params.get("demandUse").toString();//说明
		String dealResult = params.get("dealResult") == null ? "" : params.get("dealResult").toString();//处理结果
		String backCause=params.get("backCause")==null ? "" : params.get("backCause").toString();//退回原因
		String demandid=params.get("demandid").toString();//ID
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
		String time=dateFormat.format(date);//时间
		params.clear();params.put("token", token);params.put("demandUse", demandUse);
		params.put("dealResult", dealResult);params.put("backCause", backCause);
		params.put("backCause", backCause);params.put("demandid", demandid);
		params.put("time", time);params.put("backCause", backCause);
		if(dealResult.equals("1")){//对接人同意
			dockTrialDao.changTrial(params);//改状态			
			return ;
		}else{//对接人不同意
			System.out.println(2);
		}
	}
	
}
