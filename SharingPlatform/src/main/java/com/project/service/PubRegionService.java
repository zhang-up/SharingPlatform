package com.project.service;

import com.project.entity.PubRegionEntity;

import java.util.List;
import java.util.Map;

public interface PubRegionService {
	
	PubRegionEntity queryObject(String code);
	List<PubRegionEntity> queryList(Map<String, Object> map);
    List<PubRegionEntity> findALL();
    List<PubRegionEntity> findList(PubRegionEntity pubRegion);
	int queryTotal(Map<String, Object> map);
	
	void save(PubRegionEntity pubRegion);
	
	void update(PubRegionEntity pubRegion);
	
	void delete(String code);
	
	void deleteBatch(String[] codes);
}
