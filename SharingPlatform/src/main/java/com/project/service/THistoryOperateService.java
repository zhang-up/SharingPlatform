package com.project.service;

import com.project.entity.THistoryOperateEntity;

import java.util.List;
import java.util.Map;

public interface THistoryOperateService {
	
	THistoryOperateEntity queryObject(String operateId);
	List<THistoryOperateEntity> queryList(Map<String, Object> map);
    List<THistoryOperateEntity> findALL();
    List<THistoryOperateEntity> findList(THistoryOperateEntity tHistoryOperate);
	int queryTotal(Map<String, Object> map);
	
	void save(THistoryOperateEntity tHistoryOperate);
	
	void update(THistoryOperateEntity tHistoryOperate);
	
	void delete(String operateId);
	
	void deleteBatch(String[] operateIds);
}
