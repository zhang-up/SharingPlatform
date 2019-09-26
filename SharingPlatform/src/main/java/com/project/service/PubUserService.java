package com.project.service;

import com.project.entity.PubUserEntity;

import java.util.List;
import java.util.Map;

public interface PubUserService {
	
	PubUserEntity queryObject(String id);
	List<PubUserEntity> queryList(Map<String, Object> map);
    List<PubUserEntity> findALL();
    List<PubUserEntity> findList(PubUserEntity pubUser);
	int queryTotal(Map<String, Object> map);
	
	void save(PubUserEntity pubUser);
	
	void update(PubUserEntity pubUser);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
