package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.project.service.PubOrganTypeService;
import com.project.dao.PubOrganTypeDao;
import com.project.entity.PubOrganTypeEntity;



@Service("pubOrganTypeService")
public class PubOrganTypeServiceImpl implements PubOrganTypeService {
	@Autowired
	private PubOrganTypeDao pubOrganTypeDao;
	
	@Override
	public PubOrganTypeEntity queryObject(String id){
		return pubOrganTypeDao.queryObject(id);
	}
	
	@Override
	public List<PubOrganTypeEntity> queryList(Map<String, Object> map){
		return pubOrganTypeDao.queryList(map);
	}

	@Override
	public  List<PubOrganTypeEntity> findALL(){
        return pubOrganTypeDao.findALL();
	}

    @Override
    public  List<PubOrganTypeEntity> findList(PubOrganTypeEntity pubOrganType) {
        return pubOrganTypeDao.findList(pubOrganType);
    }

	@Override
	public int queryTotal(Map<String, Object> map){
		return pubOrganTypeDao.queryTotal(map);
	}
	
	@Override
	public void save(PubOrganTypeEntity pubOrganType){
		pubOrganTypeDao.save(pubOrganType);
	}
	
	@Override
	public void update(PubOrganTypeEntity pubOrganType){
		pubOrganTypeDao.update(pubOrganType);
	}
	
	@Override
	public void delete(String id){
		pubOrganTypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		pubOrganTypeDao.deleteBatch(ids);
	}
	
}
