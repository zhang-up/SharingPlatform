package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.project.service.PubUserService;
import com.project.dao.PubUserDao;
import com.project.entity.PubUserEntity;



@Service("pubUserService")
public class PubUserServiceImpl implements PubUserService {
	@Autowired
	private PubUserDao pubUserDao;
	
	@Override
	public PubUserEntity queryObject(String id){
		return pubUserDao.queryObject(id);
	}
	
	@Override
	public List<PubUserEntity> queryList(Map<String, Object> map){
		return pubUserDao.queryList(map);
	}

	@Override
	public  List<PubUserEntity> findALL(){
        return pubUserDao.findALL();
	}

    @Override
    public  List<PubUserEntity> findList(PubUserEntity pubUser) {
        return pubUserDao.findList(pubUser);
    }

	@Override
	public int queryTotal(Map<String, Object> map){
		return pubUserDao.queryTotal(map);
	}
	
	@Override
	public void save(PubUserEntity pubUser){
		pubUserDao.save(pubUser);
	}
	
	@Override
	public void update(PubUserEntity pubUser){
		pubUserDao.update(pubUser);
	}
	
	@Override
	public void delete(String id){
		pubUserDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		pubUserDao.deleteBatch(ids);
	}
	
}
