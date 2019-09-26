package com.com.project.service;

import com.project.entity.THistoryDataEntity;
import com.project.info.THistoryDataInfo;
import com.project.info.THistoryStatisticInfo;

import java.util.List;
import java.util.Map;

public interface THistoryDataService {
	
	THistoryDataEntity queryObject(String historyId);
	List<THistoryDataEntity> queryList(Map<String, Object> map);
    List<THistoryDataEntity> findALL();
    List<THistoryDataEntity> findList(THistoryDataEntity tHistoryData);
	int queryTotal(Map<String, Object> map);
	
	void save(THistoryDataEntity tHistoryData);
	
	void update(THistoryDataEntity tHistoryData);
	
	void delete(String historyId, String userId);
	
	void deleteBatch(String[] historyIds);
	
	void insertHistory(Map<String, Object> map);
	
	THistoryDataInfo findHistoryList(Map<String, Object> map);
	
	void recall(Map<String, Object> map);
	
	//提供方统计
    List<THistoryStatisticInfo> historyStatisticPro(Map<String, Object> map);
}
