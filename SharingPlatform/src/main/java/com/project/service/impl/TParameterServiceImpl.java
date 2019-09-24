package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.project.service.TParameterService;
import com.project.dao.TParameterDao;
import com.project.entity.TParameterEntity;
import com.project.utils.StringUtil;



@Service("tParameterService")
public class TParameterServiceImpl implements TParameterService {
	@Autowired
	private TParameterDao tParameterDao;
	
	@Override
	public TParameterEntity queryObject(String parameterId){
		return tParameterDao.queryObject(parameterId);
	}
	
	@Override
	public List<TParameterEntity> queryList(Map<String, Object> map){
		return tParameterDao.queryList(map);
	}

	@Override
	public  List<TParameterEntity> findALL(){
        return tParameterDao.findALL();
	}

    @Override
    public  List<TParameterEntity> findList(TParameterEntity tParameter) {
        return tParameterDao.findList(tParameter);
    }
    
    @Override
	public List<TParameterEntity> findListByCode(Map<String, Object> map) {
    	String tableName = map.get("tableName").toString();
		String columnName = map.get("columnName").toString();
		if(StringUtil.isNull(tableName)){
			tableName="";
		}
		if(StringUtil.isNull(columnName)){
			columnName="";
		}
		TParameterEntity tParameter = new TParameterEntity();
		tParameter.setParCode(tableName.toUpperCase()+"|"+columnName.toUpperCase());
		return tParameterDao.findList(tParameter);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return tParameterDao.queryTotal(map);
	}
	
	@Override
	public void save(TParameterEntity tParameter){
		tParameterDao.save(tParameter);
	}
	
	@Override
	public void update(TParameterEntity tParameter){
		tParameterDao.update(tParameter);
	}
	
	@Override
	public void delete(String parameterId){
		tParameterDao.delete(parameterId);
	}
	
	@Override
	public void deleteBatch(String[] parameterIds){
		tParameterDao.deleteBatch(parameterIds);
	}

}
