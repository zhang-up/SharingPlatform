package com.project.service;

import com.project.entity.TDemandEntity;
import com.project.entity.TDemandOperateEntity;
import com.project.entity.TImportDetailEntity;
import com.project.entity.TImportEntity;

import java.util.List;
import java.util.Map;

public interface TImportService {
	
	TImportEntity queryObject(String importId);
	List<TImportEntity> queryList(Map<String, Object> map);
    List<TImportEntity> findALL();
    List<TImportEntity> findList(TImportEntity tImport);
	int queryTotal(Map<String, Object> map);
	
	void save(TImportEntity tImport);
	
	void update(TImportEntity tImport);
	
	void delete(String importId);
	
	void deleteBatch(String[] importIds);
	
	void saveInport(TImportEntity tie,List<TDemandEntity> tedList,List<TDemandOperateEntity> tdoeList,List<TImportDetailEntity> tideList);
}
