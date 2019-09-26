package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.PubRegionDao;
import com.project.entity.PubRegionEntity;
import com.project.service.PubRegionService;



@Service("pubRegionService")
public class PubRegionServiceImpl implements PubRegionService {
	@Autowired
	private PubRegionDao pubRegionDao;
	
	@Override
	public PubRegionEntity queryObject(String code){
		return pubRegionDao.queryObject(code);
	}
	
	@Override
	public List<PubRegionEntity> queryList(Map<String, Object> map){
		return pubRegionDao.queryList(map);
	}

	@Override
	public  List<PubRegionEntity> findALL(){
        return pubRegionDao.findALL();
	}

    @Override
    public  List<PubRegionEntity> findList(PubRegionEntity pubRegion) {
        return pubRegionDao.findList(pubRegion);
    }

	@Override
	public int queryTotal(Map<String, Object> map){
		return pubRegionDao.queryTotal(map);
	}
	
	@Override
	public void save(PubRegionEntity pubRegion){
		pubRegionDao.save(pubRegion);
	}
	
	@Override
	public void update(PubRegionEntity pubRegion){
		pubRegionDao.update(pubRegion);
	}
	
	@Override
	public void delete(String code){
		pubRegionDao.delete(code);
	}
	
	@Override
	public void deleteBatch(String[] codes){
		pubRegionDao.deleteBatch(codes);
	}
	
}
