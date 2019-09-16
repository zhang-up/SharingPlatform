package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.TDemandDao;
import com.project.dao.TDemandOperateDao;
import com.project.entity.TDemandEntity;
import com.project.entity.TDemandOperateEntity;
import com.project.info.TDemandInfo;
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
	
	@Override
	public TDemandEntity queryObject(String demandId){
		return tDemandDao.queryObject(demandId);
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
	public void edit(TDemandEntity tDemand, String userId){
		String demandId = tDemand.getDemandId();
		if(StringUtil.isNull(demandId)){
			demandId = UUIDUtil.getUUID32();
			tDemand.setDemandId(demandId);
			tDemand.setSaveTime(DateUtil.getDate());
			tDemandDao.save(tDemand);
		}else{
			TDemandEntity td = tDemandDao.queryObject(demandId);
			td.updateValue(tDemand);
			tDemand.setSaveTime(DateUtil.getDate());
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
	public void save(TDemandEntity tDemand){
		tDemandDao.save(tDemand);
	}
	
	@Override
	public void update(TDemandEntity tDemand){
		tDemandDao.update(tDemand);
	}
	
	@Override
	public void delete(String demandId){
		tDemandDao.delete(demandId);
	}
	
	@Override
	public void deleteBatch(String[] demandIds){
		tDemandDao.deleteBatch(demandIds);
	}
	
}
