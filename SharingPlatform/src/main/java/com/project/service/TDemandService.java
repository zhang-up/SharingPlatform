package com.project.service;

import com.project.entity.TDemandEntity;

import java.util.List;
import java.util.Map;

public interface TDemandService {
	
	TDemandEntity queryObject(String demandId);
	List<TDemandEntity> queryList(Map<String, Object> map);
    List<TDemandEntity> findALL();
    List<TDemandEntity> findList(TDemandEntity tDemand);
	int queryTotal(Map<String, Object> map);
	
	void edit(TDemandEntity tDemand, String userId);
	
	void save(TDemandEntity tDemand);
	
	void update(TDemandEntity tDemand);
	
	void delete(String demandId);
	
	void deleteBatch(String[] demandIds);
}
