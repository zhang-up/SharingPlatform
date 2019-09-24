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
import com.project.exception.RRException;
import com.project.info.THistoryDataInfo;
import com.project.utils.DateUtil;
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
		map.put("historyId", UUIDUtil.getUUID32());
		map.put("saveTime", DateUtil.getDate());
		String period=map.get("h_startTime")+"--"+map.get("h_endTime");
		map.put("period", period);		
		map.put("operateId", UUIDUtil.getUUID32());
		System.out.println(map);
		tHistoryDataDao.insertHistory(map);
		tHistoryOperateDao.insertHistory_operate(map);
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
	public THistoryDataInfo infoDetail(String id) {
		return tHistoryDataDao.findHistoryList(id);
	}
	
}
