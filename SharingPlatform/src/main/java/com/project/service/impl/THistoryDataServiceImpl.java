package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.project.service.THistoryDataService;
import com.project.dao.THistoryDataDao;
import com.project.dao.THistoryOperateDao;
import com.project.entity.TDemandEntity;
import com.project.entity.THistoryDataEntity;
import com.project.entity.THistoryOperateEntity;
import com.project.exception.RRException;
import com.project.info.THistoryDataInfo;
import com.project.info.THistoryStatisticInfo;
import com.project.utils.DateUtil;
import com.project.utils.StringUtil;
import com.project.utils.UUIDUtil;



@Service("tHistoryDataService")
public class THistoryDataServiceImpl implements THistoryDataService {
	@Autowired
	private THistoryDataDao tHistoryDataDao;
	
	@Autowired
	private THistoryOperateDao tHistoryOperateDao;
	
	@Override
	public THistoryDataEntity queryObject(String historyId){
		return tHistoryDataDao.queryObject(historyId);
	}
	
	@Override
	public List<THistoryDataEntity> queryList(Map<String, Object> map){
		return tHistoryDataDao.findHistory(map);
	}

	@Override
	public  List<THistoryDataEntity> findALL(){
        return tHistoryDataDao.findALL();
	}

    @Override
    public  List<THistoryDataEntity> findList(THistoryDataEntity tHistoryData) {
        return tHistoryDataDao.findList(tHistoryData);
    }

	@Override
	public int queryTotal(Map<String, Object> map){
		return tHistoryDataDao.queryTotal(map);
	}	
	/*
	 *插入
	 */
	@Override
	public void insertHistory(Map<String, Object> map){	
		THistoryDataEntity th=new THistoryDataEntity();		
		String state=map.get("state").toString();
	//	map.put("historyId", UUIDUtil.getUUID32());
		map.put("saveTime", DateUtil.getDate());
		String historyId=UUIDUtil.getUUID32();
		String period=map.get("h_startTime")+"至"+map.get("h_endTime");
		//map.put("period", period);	
		String time=DateUtil.getDate();
		
		th.setHistoryId(historyId);
		th.setProvideDep(map.get("provide_dep").toString());
		th.setHisName(map.get("h_demandName").toString());
		th.setHisDetail(map.get("h_demantDetail").toString());
		th.setPeriod(period);
		th.setSaveTime(time);
		th.setCreater(map.get("userId").toString());
		th.setState(state);
		th.setRemark(map.get("h_demandUse").toString());
		
		//operate表
		String operateId=UUIDUtil.getUUID32();
		THistoryOperateEntity  tho=new THistoryOperateEntity();
		tho.setOperateId(operateId);
		tho.setHistoryId(historyId);
		tho.setOperator(map.get("userId").toString());		
		tho.setOperateTime(time);
		tho.setOperate_res(map.get("h_dealResult").toString());
		tho.setState(state);
		tho.setRemark(map.get("h_demandUse").toString());
				
		if(map.get("history_id")==""){	//添加
			tHistoryDataDao.insertHistory(th);
			tHistoryOperateDao.insertHistory_operate(tho);
		}else{
			if("00".equals(state)){//草稿
				tho.setHistoryId(map.get("history_id").toString());
				th.setHistoryId(map.get("history_id").toString());
				tHistoryDataDao.changDraftHistory(th);   //更改草稿，改Data表数据
				tHistoryOperateDao.changDraftHistoryOperate(tho);   //更改operate表数据
			}else if("01".equals(state)){//已提交
				map.put("historyId", map.get("history_id").toString());
				tho.setHistoryId(map.get("history_id").toString());
				th.setHistoryId(map.get("history_id").toString());
				tHistoryDataDao.changDraftHistory(th);//更改草稿，改Data表数据
				tHistoryOperateDao.insertHistory_operate(tho);//新插入operate表数据
			}
		}	
	}
	
	@Override
	public void save(THistoryDataEntity tHistoryData){
		tHistoryDataDao.save(tHistoryData);
	}
	
	@Override
	public void update(THistoryDataEntity tHistoryData){
		tHistoryDataDao.update(tHistoryData);
	}
	
	@Override
	public void delete(String historyId, String userId){
		
		THistoryDataEntity tDataEntity=tHistoryDataDao.queryObject(historyId);
		if(!userId.equals(tDataEntity.getCreater())){
			throw new RRException("您没有删除该记录的权限！");
		}
		tHistoryOperateDao.deleteByData(historyId);
		tHistoryDataDao.delete(historyId);
		

	}
	
	@Override
	public void deleteBatch(String[] historyIds){
		tHistoryDataDao.deleteBatch(historyIds);
	}

	@Override
	public THistoryDataInfo findHistoryList(Map<String, Object> map) {		
		//String operateTime=tho.getOperateTime();
		//String remark=tho.getRemark();//operate表中数据		
		THistoryDataInfo tHistoryDataInfo=new THistoryDataInfo();
		tHistoryDataInfo=tHistoryDataDao.findHistoryDetails(map);		
		String aString=tHistoryDataInfo.getPeriod();
		tHistoryDataInfo.setBeforeT(aString.substring(0,10));//将数据周期截取
		tHistoryDataInfo.setLastT(aString.substring(11));
		THistoryOperateEntity tho=new THistoryOperateEntity();	
		tho=tHistoryOperateDao.findOperate(map);
		if(!(tho==null)){
			String operateTime = tho.getOperateTime() == null ? "" : tho.getOperateTime().toString();		
			String remark = tho.getRemark() == null ? "" : tho.getRemark().toString();
			tHistoryDataInfo.setOperateTime(operateTime);
			tHistoryDataInfo.setResultUse(remark);
		}
		return tHistoryDataInfo;
	}

	@Override
	public void recall(Map<String, Object> map) {
		String historyId = map.get("historyId") == null ? "" : map.get("historyId").toString();
		String userId=tHistoryDataDao.findHistoryCode(historyId);//查询当前操作行的userId	
		String time=DateUtil.getDate();map.put("saveTime", time);
		if(userId.equals(map.get("userId"))){//只有当前用户才能操作撤退
			THistoryOperateEntity  tho=new THistoryOperateEntity();
			tHistoryDataDao.changHistoryState(map);//改historyData表状态
			THistoryOperateEntity thistory=tHistoryOperateDao.findOperateRes(map);//查找操作表的处理结果
			String res = thistory.getOperate_res() == null ? "" : thistory.getOperate_res().toString();
/*			map.put("h_dealResult", res);//操作结果是否对接平台
			map.put("state", "02");//状态
			map.put("h_demandUse", map.get("remark"));//说明
			map.put("operateId", UUIDUtil.getUUID32());*/
			String  operateId=UUIDUtil.getUUID32();
			tho.setOperateId(operateId);
			tho.setHistoryId(historyId);
			tho.setOperator(userId);		
			tho.setOperateTime(time);
			tho.setOperate_res(res);
			tho.setState("02");
			tho.setRemark(map.get("remark").toString());			
			tHistoryOperateDao.insertHistory_operate(tho);//新增撤退状态
		}else if(!userId.equals(map.get("userId"))){
			throw new RRException("您没有撤退该记录的权限！");
		}
	}

	@Override
	public List<THistoryStatisticInfo> historyStatisticPro(Map<String, Object> map) {
		String sidx = map.get("sidx") == null ? "" : map.get("sidx").toString();
		if(!StringUtil.isNull(sidx)){
			if("provideDepName".equals(sidx)){
				map.put("sidx","my_view.provide_dep_name");
			}else if("shouldProNums".equals(sidx)){
				map.put("sidx","my_view.should_pro_nums");
			}else if("alreadyProNums".equals(sidx)){
				map.put("sidx","my_view.already_pro_nums");
			}else if("notProNums".equals(sidx)){
				map.put("sidx","my_view.not_pro_nums");
			}else if("appliedForNums".equals(sidx)){
				map.put("sidx","my_view.applied_for_nums");
			}else if("firstTrialNums".equals(sidx)){
				map.put("sidx","my_view.first_trial_nums");
			}else if("confirmedNums".equals(sidx)){
				map.put("sidx","my_view.confirmed_nums");
			}else if("rescindedNums".equals(sidx)){
				map.put("sidx","my_view.rescinded_nums");
			}
		}
		return tHistoryDataDao.historyStatisticPro(map);
	}
	
}
