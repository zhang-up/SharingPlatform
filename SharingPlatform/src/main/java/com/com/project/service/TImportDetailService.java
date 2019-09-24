package com.com.project.service;

import com.project.entity.TImportDetailEntity;

import java.util.List;
import java.util.Map;

public interface TImportDetailService {
	
	TImportDetailEntity queryObject(String importDetailId);
	List<TImportDetailEntity> queryList(Map<String, Object> map);
    List<TImportDetailEntity> findALL();
    List<TImportDetailEntity> findList(TImportDetailEntity tImportDetail);
	int queryTotal(Map<String, Object> map);
	
	void save(TImportDetailEntity tImportDetail);
	
	void update(TImportDetailEntity tImportDetail);
	
	void delete(String importDetailId);
	
	void deleteBatch(String[] importDetailIds);
}
