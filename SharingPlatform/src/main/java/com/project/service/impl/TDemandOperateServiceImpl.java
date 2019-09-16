package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.TDemandOperateDao;
import com.project.entity.TDemandOperateEntity;
import com.project.service.TDemandOperateService;



@Service("tDemandOperateService")
public class TDemandOperateServiceImpl implements TDemandOperateService {
	@Autowired
	private TDemandOperateDao tDemandOperateDao;
	
	@Override
	public TDemandOperateEntity queryObject(String operateId){
		return tDemandOperateDao.queryObject(operateId);
	}
	
	@Override
	public List<TDemandOperateEntity> queryList(Map<String, Object> map){
		return tDemandOperateDao.queryList(map);
	}

	@Override
	public  List<TDemandOperateEntity> findALL(){
        return tDemandOperateDao.findALL();
	}

    @Override
    public  List<TDemandOperateEntity> findList(TDemandOperateEntity tDemandOperate) {
        return tDemandOperateDao.findList(tDemandOperate);
    }

	@Override
	public int queryTotal(Map<String, Object> map){
		return tDemandOperateDao.queryTotal(map);
	}
	
	@Override
	public void save(TDemandOperateEntity tDemandOperate){
		tDemandOperateDao.save(tDemandOperate);
	}
	
	@Override
	public void update(TDemandOperateEntity tDemandOperate){
		tDemandOperateDao.update(tDemandOperate);
	}
	
	@Override
	public void delete(String operateId){
		tDemandOperateDao.delete(operateId);
	}
	
	@Override
	public void deleteBatch(String[] operateIds){
		tDemandOperateDao.deleteBatch(operateIds);
	}
	
}
