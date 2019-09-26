package com.project.service;

import com.project.entity.TParameterEntity;

import java.util.List;
import java.util.Map;

public interface TParameterService {
	
	TParameterEntity queryObject(String parameterId);
	List<TParameterEntity> queryList(Map<String, Object> map);
    List<TParameterEntity> findALL();
    List<TParameterEntity> findList(TParameterEntity tParameter);
    List<TParameterEntity> findListByCode(Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
	
	void save(TParameterEntity tParameter);
	
	void update(TParameterEntity tParameter);
	
	void delete(String parameterId);
	
	void deleteBatch(String[] parameterIds);
}
