package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.entity.TDemandEntity;
import com.project.info.DockTrialInfo;
import com.project.info.TDemandInfo;

public interface TDemandService {
	
	TDemandEntity queryObject(String demandId);
	TDemandInfo queryDetailObject(String id);
	List<TDemandEntity> queryList(Map<String, Object> map);
    List<TDemandEntity> findALL();
    List<TDemandEntity> findList(TDemandEntity tDemand);
	int queryTotal(Map<String, Object> map);
	
	List<TDemandInfo> queryApplyList(Map<String, Object> map);
	int queryApplyTotal(Map<String, Object> map);
	
	void edit(TDemandEntity tDemand, String userId);
	
	void save(TDemandEntity tDemand);
	
	void update(TDemandEntity tDemand);
	
	void delete(String demandId, String userId);
	
	void deleteBatch(String[] demandIds);
	
	 List<TDemandInfo> dockingList(Map<String, Object> map);

}
