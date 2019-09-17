package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.THistoryDataDao;
import com.project.entity.THistoryDataEntity;
import com.project.service.THistoryDataService;



@Service("tHistoryDataService")
public class THistoryDataServiceImpl implements THistoryDataService {
	@Autowired
	private THistoryDataDao tHistoryDataDao;
	
	@Override
	public THistoryDataEntity queryObject(String historyId){
		return tHistoryDataDao.queryObject(historyId);
	}
	
	@Override
	public List<THistoryDataEntity> queryList(Map<String, Object> map){
		return tHistoryDataDao.queryList(map);
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
	
	@Override
	public void save(THistoryDataEntity tHistoryData){
		tHistoryDataDao.save(tHistoryData);
	}
	
	@Override
	public void update(THistoryDataEntity tHistoryData){
		tHistoryDataDao.update(tHistoryData);
	}
	
	@Override
	public void delete(String historyId){
		tHistoryDataDao.delete(historyId);
	}
	
	@Override
	public void deleteBatch(String[] historyIds){
		tHistoryDataDao.deleteBatch(historyIds);
	}
	
}
