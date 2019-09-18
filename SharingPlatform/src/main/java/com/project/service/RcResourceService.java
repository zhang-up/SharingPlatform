package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.entity.RcResourceEntity;

public interface RcResourceService {
	
	RcResourceEntity queryObject(String id);
	List<RcResourceEntity> queryList(Map<String, Object> map);
    List<RcResourceEntity> findALL();
    List<RcResourceEntity> findList(RcResourceEntity rcResource);
	int queryTotal(Map<String, Object> map);
	
	void save(RcResourceEntity rcResource);
	
	void update(RcResourceEntity rcResource);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
