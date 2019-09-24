package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.project.service.PubOrganTreeService;
import com.project.dao.PubOrganTreeDao;
import com.project.entity.PubOrganTreeEntity;



@Service("pubOrganTreeService")
public class PubOrganTreeServiceImpl implements PubOrganTreeService {
	@Autowired
	private PubOrganTreeDao pubOrganTreeDao;
	
	@Override
	public PubOrganTreeEntity queryObject(String id){
		return pubOrganTreeDao.queryObject(id);
	}
	
	@Override
	public List<PubOrganTreeEntity> queryList(Map<String, Object> map){
		return pubOrganTreeDao.queryList(map);
	}

	@Override
	public  List<PubOrganTreeEntity> findALL(){
        return pubOrganTreeDao.findALL();
	}

    @Override
    public  List<PubOrganTreeEntity> findList(PubOrganTreeEntity pubOrganTree) {
        return pubOrganTreeDao.findList(pubOrganTree);
    }

	@Override
	public int queryTotal(Map<String, Object> map){
		return pubOrganTreeDao.queryTotal(map);
	}
	
	@Override
	public void save(PubOrganTreeEntity pubOrganTree){
		pubOrganTreeDao.save(pubOrganTree);
	}
	
	@Override
	public void update(PubOrganTreeEntity pubOrganTree){
		pubOrganTreeDao.update(pubOrganTree);
	}
	
	@Override
	public void delete(String id){
		pubOrganTreeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		pubOrganTreeDao.deleteBatch(ids);
	}
	
}
