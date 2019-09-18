package com.project.service;

import com.project.entity.TDemandOperateEntity;
import com.project.info.TDemandOperateInfo;

import java.util.List;
import java.util.Map;

public interface TDemandOperateService {
	
	TDemandOperateEntity queryObject(String operateId);
	List<TDemandOperateEntity> queryList(Map<String, Object> map);
    List<TDemandOperateEntity> findALL();
    List<TDemandOperateEntity> findList(TDemandOperateEntity tDemandOperate);
	int queryTotal(Map<String, Object> map);
	
	void save(TDemandOperateEntity tDemandOperate);
	
	void update(TDemandOperateEntity tDemandOperate);
	
	void delete(String operateId);
	
	void deleteBatch(String[] operateIds);
	
	List<TDemandOperateInfo> queryListByDemand(Object demandId);
}
