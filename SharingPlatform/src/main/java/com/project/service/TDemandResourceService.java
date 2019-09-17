package com.project.service;

import com.project.entity.TDemandResourceEntity;

import java.util.List;
import java.util.Map;

public interface TDemandResourceService {
	
	TDemandResourceEntity queryObject(String dResId);
	List<TDemandResourceEntity> queryList(Map<String, Object> map);
    List<TDemandResourceEntity> findALL();
    List<TDemandResourceEntity> findList(TDemandResourceEntity tDemandResource);
	int queryTotal(Map<String, Object> map);
	
	void save(TDemandResourceEntity tDemandResource);
	
	void update(TDemandResourceEntity tDemandResource);
	
	void delete(String dResId);
	
	void deleteBatch(String[] dResIds);
}
