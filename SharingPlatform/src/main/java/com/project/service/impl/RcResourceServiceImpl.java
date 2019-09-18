package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.RcResourceDao;
import com.project.entity.RcResourceEntity;
import com.project.service.RcResourceService;



@Service("rcResourceService")
public class RcResourceServiceImpl implements RcResourceService {
	@Autowired
	private RcResourceDao rcResourceDao;
	
	@Override
	public RcResourceEntity queryObject(String id){
		return rcResourceDao.queryObject(id);
	}
	
	@Override
	public List<RcResourceEntity> queryList(Map<String, Object> map){
		return rcResourceDao.queryList(map);
	}

	@Override
	public  List<RcResourceEntity> findALL(){
        return rcResourceDao.findALL();
	}

    @Override
    public  List<RcResourceEntity> findList(RcResourceEntity rcResource) {
        return rcResourceDao.findList(rcResource);
    }

	@Override
	public int queryTotal(Map<String, Object> map){
		return rcResourceDao.queryTotal(map);
	}
	
	@Override
	public void save(RcResourceEntity rcResource){
		rcResourceDao.save(rcResource);
	}
	
	@Override
	public void update(RcResourceEntity rcResource){
		rcResourceDao.update(rcResource);
	}
	
	@Override
	public void delete(String id){
		rcResourceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		rcResourceDao.deleteBatch(ids);
	}
	
}
