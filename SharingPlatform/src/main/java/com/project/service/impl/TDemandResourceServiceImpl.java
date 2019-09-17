package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.TDemandResourceDao;
import com.project.entity.TDemandResourceEntity;
import com.project.service.TDemandResourceService;



@Service("tDemandResourceService")
public class TDemandResourceServiceImpl implements TDemandResourceService {
	@Autowired
	private TDemandResourceDao tDemandResourceDao;
	
	@Override
	public TDemandResourceEntity queryObject(String dResId){
		return tDemandResourceDao.queryObject(dResId);
	}
	
	@Override
	public List<TDemandResourceEntity> queryList(Map<String, Object> map){
		return tDemandResourceDao.queryList(map);
	}

	@Override
	public  List<TDemandResourceEntity> findALL(){
        return tDemandResourceDao.findALL();
	}

    @Override
    public  List<TDemandResourceEntity> findList(TDemandResourceEntity tDemandResource) {
        return tDemandResourceDao.findList(tDemandResource);
    }

	@Override
	public int queryTotal(Map<String, Object> map){
		return tDemandResourceDao.queryTotal(map);
	}
	
	@Override
	public void save(TDemandResourceEntity tDemandResource){
		tDemandResourceDao.save(tDemandResource);
	}
	
	@Override
	public void update(TDemandResourceEntity tDemandResource){
		tDemandResourceDao.update(tDemandResource);
	}
	
	@Override
	public void delete(String dResId){
		tDemandResourceDao.delete(dResId);
	}
	
	@Override
	public void deleteBatch(String[] dResIds){
		tDemandResourceDao.deleteBatch(dResIds);
	}
	
}
