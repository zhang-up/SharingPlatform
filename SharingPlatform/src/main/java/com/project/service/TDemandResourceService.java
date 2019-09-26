package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.entity.TDemandResourceEntity;
import com.project.info.TDemandResourceInfo;

public interface TDemandResourceService {
	
	TDemandResourceEntity queryObject(String dResId);
	List<TDemandResourceEntity> queryList(Map<String, Object> map);
    List<TDemandResourceEntity> findALL();
    List<TDemandResourceEntity> findList(TDemandResourceEntity tDemandResource);
	int queryTotal(Map<String, Object> map);
	
	List<TDemandResourceInfo> findInfoListByDemand(Object demandId);
	List<TDemandResourceEntity> findListByDemand(Object demandId);
	
	void save(TDemandResourceEntity tDemandResource);
	
	void update(TDemandResourceEntity tDemandResource);
	
	void delete(String dResId);
	
	void deleteBatch(String[] dResIds);
}
