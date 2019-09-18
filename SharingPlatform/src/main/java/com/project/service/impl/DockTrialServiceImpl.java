package com.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.DockTrialDao;
import com.project.entity.TDemandOperateEntity;
import com.project.info.DockTrialInfo;
import com.project.service.DockTrialService;
import com.project.utils.DateUtil;
import com.project.utils.UUIDUtil;

@Service
@Transactional
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
		String demandOperateId = UUIDUtil.getUUID32();
		String time=DateUtil.getDate();//时间
		params.clear();params.put("token", token);params.put("demandUse", demandUse);
		params.put("dealResult", dealResult);
		params.put("backCause", backCause);params.put("demandid", demandid);
		params.put("time", time);params.put("demandOperateId", demandOperateId);
		if(dealResult.equals("1")){//对接人同意
			params.put("state", "02");
			params.put("res", "1");
			params.put("cause", "0");
			dockTrialDao.changTrial(params);//改状态	
			dockTrialDao.insertAgreeTrial(params);//插入新数据
			return ;
		}else if(dealResult.equals("2")){//对接人不同意
			params.put("state", "03");
			params.put("res", "2");
			params.put("cause", backCause);
			dockTrialDao.changTrial(params);//改状态	
			dockTrialDao.insertAgreeTrial(params);//插入新数据
			return ;
		}
	}
	
	/*
	 * 
	 */
	@Override
	public DockTrialInfo findTrialPeople(Map<String, Object> map) {
		DockTrialInfo dockTrialInfo=new DockTrialInfo();
		dockTrialInfo=dockTrialDao.findTrialPeople(map);
		String trialPeople=dockTrialInfo.getTrialPeople();
		String dealTime=dockTrialInfo.getDealTime();
		String dealResult=dockTrialInfo.getDealResult();		
		dockTrialInfo=dockTrialDao.trialInfoList(map);
		dockTrialInfo.setTrialPeople(trialPeople);
		dockTrialInfo.setDealTime(dealTime);
		dockTrialInfo.setDealResult(dealResult);
		if(dockTrialInfo.getDealResult().equals("1")){
			dockTrialInfo.setDealResult("同意");
		}else if(dockTrialInfo.getDealResult().equals("2")){
			dockTrialInfo.setDealResult("回退");
		}
		return dockTrialInfo;
	}
	@Override
	public DockTrialInfo finishVerify(Map<String, Object> map) {
		DockTrialInfo dockTrialInfo=new DockTrialInfo();
		dockTrialInfo=dockTrialDao.findTrialPeople(map);
		String trialPeople=dockTrialInfo.getTrialPeople();
		String dealTime=dockTrialInfo.getDealTime();
		String dealResult=dockTrialInfo.getDealResult();
		dockTrialInfo=dockTrialDao.findProvidePeople(map);
		String providePeople=dockTrialInfo.getProvidePeople();//提供单位处理人
		String provideDealTime=dockTrialInfo.getProvideDealTime();//提供单位处理时间
		//String provideDealResult=dockTrialInfo.getProvideDealResult();//提供单位处理结果
		dockTrialInfo=dockTrialDao.trialInfoList(map);
		dockTrialInfo.setTrialPeople(trialPeople);
		dockTrialInfo.setDealTime(dealTime);
		dockTrialInfo.setDealResult(dealResult);
		
		dockTrialInfo.setProvidePeople(providePeople);
		dockTrialInfo.setProvideDealTime(provideDealTime);
		dockTrialInfo.setProvideDealResult("通过");
		if(dockTrialInfo.getDealResult().equals("1")){
			dockTrialInfo.setDealResult("同意");
		}else if(dockTrialInfo.getDealResult().equals("2")){
			dockTrialInfo.setDealResult("回退");
		}
		return dockTrialInfo;
	}	
  /*
   * 提供方处理消息
   */
	@Override
	public void dealProvide(Map<String, Object> map) {
		String token = map.get("token") == null ? "" : map.get("token").toString();//用户ID
		String demandUse = map.get("demandUse") == null ? "" : map.get("demandUse").toString();//说明
		String dealResult = map.get("dealResult") == null ? "" : map.get("dealResult").toString();//处理结果(1-同意，2-驳回)
		String dealReasonYes=map.get("dealReasonYes")==null ? "" : map.get("dealReasonYes").toString();//同意原因
		String dealReasonNo=map.get("dealReasonNo")==null ? "" : map.get("dealReasonNo").toString();//驳回原因
		String demandid=map.get("demandid").toString();//ID
		String demandOperateId = UUIDUtil.getUUID32();
		String time=DateUtil.getDate();//时间
		map.clear();map.put("token", token);map.put("demandUse", demandUse);map.put("time", time);
		map.put("demandid", demandid);map.put("token",token );map.put("demandOperateId",demandOperateId );
		if(dealResult.equals("1")){//提供人同意
			map.put("state", "04");
			map.put("res", "1");
			map.put("cause", dealReasonYes);
			 dockTrialDao.changTrial(map);//需求表改状态	
			 dockTrialDao.insertAgreeTrial(map);//插入新数据
			return ;
		}else if(dealResult.equals("2")){//提供人不同意
			map.put("state", "05");
			map.put("res", "2");
			map.put("cause", dealReasonNo);
			dockTrialDao.changTrial(map);//需求表改状态	
			dockTrialDao.insertAgreeTrial(map);//插入新数据
			return ;
		}		
	}
	  /*
	   * 提供方处理完成确认消息
	   */
	@Override
	public void finishDleal(Map<String, Object> map) {
		map.put("state", "06");map.put("time", DateUtil.getDate());
		dockTrialDao.changTrial(map);//需求表改状态	//该状态，改成06已完成
		TDemandOperateEntity dd=dockTrialDao.findTdOperate(map);
		String res=dd.getOperateRes();
		String cause=dd.getCause();
		String fillADD=dd.getFileAdd();
		String demandOperateId = UUIDUtil.getUUID32();	
		map.put("res", res);map.put("demandOperateId", demandOperateId);
		map.put("cause", cause);map.put("file_add", fillADD);
		dockTrialDao.insertAgreeTrial(map);//插入新数据
		//插入新数据
	}
}
