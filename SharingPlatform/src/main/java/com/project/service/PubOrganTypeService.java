package com.project.service;

import com.project.entity.PubOrganTypeEntity;

import java.util.List;
import java.util.Map;

public interface PubOrganTypeService {
	
	PubOrganTypeEntity queryObject(String id);
	List<PubOrganTypeEntity> queryList(Map<String, Object> map);
    List<PubOrganTypeEntity> findALL();
    List<PubOrganTypeEntity> findList(PubOrganTypeEntity pubOrganType);
	int queryTotal(Map<String, Object> map);
	
	void save(PubOrganTypeEntity pubOrganType);
	
	void update(PubOrganTypeEntity pubOrganType);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
