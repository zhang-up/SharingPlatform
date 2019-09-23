package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.TDemandDao;
import com.project.dao.TDemandOperateDao;
import com.project.dao.TDemandResourceDao;
import com.project.entity.TDemandEntity;
import com.project.entity.TDemandOperateEntity;
import com.project.entity.TDemandResourceEntity;
import com.project.exception.RRException;
import com.project.info.RcResourceInfo;
import com.project.info.TDemandInfo;
import com.project.info.TDemandStatisticInfo;
import com.project.service.TDemandService;
import com.project.utils.DateUtil;
import com.project.utils.StringUtil;
import com.project.utils.UUIDUtil;



@Service("tDemandService")
public class TDemandServiceImpl implements TDemandService {
	@Autowired
	private TDemandDao tDemandDao;
	@Autowired
	private TDemandOperateDao tDemandOperateDao;
	@Autowired
	private TDemandResourceDao tDemandResourceDao;
	
	@Override
	public TDemandEntity queryObject(String demandId){
		return tDemandDao.queryObject(demandId);
	}
	
	@Override
	public TDemandInfo queryDetailObject(String demandId){
		return tDemandDao.queryDetailObject(demandId);
	}
	
	@Override
	public List<TDemandEntity> queryList(Map<String, Object> map){
		return tDemandDao.queryList(map);
	}

	@Override
	public  List<TDemandEntity> findALL(){
        return tDemandDao.findALL();
	}

    @Override
    public  List<TDemandEntity> findList(TDemandEntity tDemand) {
        return tDemandDao.findList(tDemand);
    }

	@Override
	public int queryTotal(Map<String, Object> map){
		return tDemandDao.queryTotal(map);
	}
	
	@Override
	public List<TDemandInfo> queryApplyList(Map<String, Object> map){
		return tDemandDao.queryInfoList(map);
	}
	
	@Override
	public int queryApplyTotal(Map<String, Object> map){
		return tDemandDao.queryTotal(map);
	}
	
	@Override
	public void edit(TDemandEntity tDemand, String userId, List<RcResourceInfo> rri, String choose_res, String hiteMatch){
		String demandId = tDemand.getDemandId();
		
		//匹配资源
		if("yes".equals(hiteMatch)){
			tDemandResourceDao.deleteByDemand(demandId);
			for(RcResourceInfo rr : rri){
				TDemandResourceEntity tdre = new TDemandResourceEntity();
				tdre.setDResId(UUIDUtil.getUUID32());
				tdre.setResourceId(rr.getId());
				tdre.setDemandId(demandId);
				String sta = rr.getId().equals(choose_res) ? "1" : "0";
				if("1".equals(sta)){
					tDemand.setState("08");
				}
				tdre.setState(sta);
				tDemandResourceDao.save(tdre);
			}
		}else{
			List<TDemandResourceEntity> tdreList = tDemandResourceDao.findListByDemand(demandId);
			for(TDemandResourceEntity tdr : tdreList){
				String resId = tdr.getResourceId();
				String sta = tdr.getState();
				
				if(resId.equals(choose_res) && "0".equals(sta)){
					tdr.setState("1");
					tDemandResourceDao.update(tdr);
					tDemand.setState("08");
				}
				if(!resId.equals(choose_res) && "1".equals(sta)){
					tdr.setState("0");
					tDemandResourceDao.update(tdr);
				}
			}
		}
		
		
		if(StringUtil.isNull(demandId)){
			demandId = UUIDUtil.getUUID32();
			tDemand.setDemandId(demandId);
			tDemand.setSaveTime(DateUtil.getDate());
			tDemandDao.save(tDemand);
		}else{
			TDemandEntity td = tDemandDao.queryObject(demandId);
			td.updateValue(tDemand);
			td.setSaveTime(DateUtil.getDate());
			tDemandDao.update(td);
		}
		
		//操作记录
		TDemandOperateEntity tdoe = new TDemandOperateEntity();
		tdoe.setOperateId(UUIDUtil.getUUID32());
		tdoe.setDemandId(demandId);
		tdoe.setOperator(userId);
		tdoe.setOperateTime(DateUtil.getDate());
		tdoe.setState(tDemand.getState());
		tdoe.setOperateRes("0");
		tdoe.setCause("0");
		tDemandOperateDao.save(tdoe);
		
	}
	
	@Override
	public void revoke(TDemandEntity tDemand, String userId){
		String demandId = tDemand.getDemandId();
		if(StringUtil.isNull(demandId)){
			throw new RRException("提交信息异常！");
		}else{
			TDemandEntity td = tDemandDao.queryObject(demandId);
			td.setState("07");
			td.setSaveTime(DateUtil.getDate());
			tDemandDao.update(td);
		}
		
		//操作记录
		TDemandOperateEntity tdoe = new TDemandOperateEntity();
		tdoe.setOperateId(UUIDUtil.getUUID32());
		tdoe.setDemandId(demandId);
		tdoe.setOperator(userId);
		tdoe.setOperateTime(DateUtil.getDate());
		tdoe.setState("07");
		tdoe.setOperateRes("0");
		tdoe.setCause("0");
		tdoe.setRemark(tDemand.getRemark());
		tDemandOperateDao.save(tdoe);
		
	}
	
	@Override
	public void save(TDemandEntity tDemand){
		tDemandDao.save(tDemand);
	}
	
	@Override
	public void update(TDemandEntity tDemand){
		tDemandDao.update(tDemand);
	}
	
	@Override
	public void delete(String demandId, String userId){
		
		TDemandEntity td = tDemandDao.queryObject(demandId);
		
		if(!userId.equals(td.getCreater())){
			throw new RRException("您没有删除该记录的权限！");
		}
		
		tDemandOperateDao.deleteByDemand(demandId);
		tDemandResourceDao.deleteByDemand(demandId);
		tDemandDao.delete(demandId);
	}
	
	@Override
	public void deleteBatch(String[] demandIds){
		tDemandDao.deleteBatch(demandIds);
	}
	
	@Override
	public List<TDemandInfo> dockingList(Map<String, Object> map) {
		return tDemandDao.queryInfoList(map);

	}

	//提供方统计
	@Override
	public List<TDemandStatisticInfo> statisticPro(Map<String, Object> map){
		
		String staTimeType = map.get("staTimeType") == null ? "" : map.get("staTimeType").toString();
		if("2".equals(staTimeType)){
			String beforeDay = map.get("beforeDay") == null ? "0" : map.get("beforeDay").toString();
			String dayDate = map.get("dayDate") == null ? "" : map.get("dayDate").toString();
			String afterDay = map.get("afterDay") == null ? "0" : map.get("afterDay").toString();
			if(StringUtil.isNull(dayDate)){
				map.put("stratDate", "");
				map.put("endDate", "");
			}else{
				int bDay = 0;
				try {
					bDay = Integer.valueOf(beforeDay);
				} catch (Exception e) {
					bDay = 0;
				}
				int aDay = 0;
				try {
					aDay = Integer.valueOf(afterDay);
				} catch (Exception e) {
					aDay = 0;
				}
				
				String stratDate = DateUtil.addDay(dayDate, (0-bDay));
				String endDate = DateUtil.addDay(dayDate, aDay);
				map.put("stratDate", stratDate);
				map.put("endDate", endDate);
			}
		}
		
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
			}else if("rejectNums".equals(sidx)){
				map.put("sidx","my_view.reject_nums");
			}else if("regressionNums".equals(sidx)){
				map.put("sidx","my_view.regression_nums");
			}else if("sharedNums".equals(sidx)){
				map.put("sidx","my_view.shared_nums");
			}else if("noFinishNums".equals(sidx)){
				map.put("sidx","my_view.no_finish_nums");
			}
		}
		
		return tDemandDao.statisticPro(map);
    }

}
