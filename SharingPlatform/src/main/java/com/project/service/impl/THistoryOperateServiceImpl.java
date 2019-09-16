package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.THistoryOperateDao;
import com.project.entity.THistoryOperateEntity;
import com.project.service.THistoryOperateService;



@Service("tHistoryOperateService")
public class THistoryOperateServiceImpl implements THistoryOperateService {
	@Autowired
	private THistoryOperateDao tHistoryOperateDao;
	
	@Override
	public THistoryOperateEntity queryObject(String operateId){
		return tHistoryOperateDao.queryObject(operateId);
	}
	
	@Override
	public List<THistoryOperateEntity> queryList(Map<String, Object> map){
		return tHistoryOperateDao.queryList(map);
	}

	@Override
	public  List<THistoryOperateEntity> findALL(){
        return tHistoryOperateDao.findALL();
	}

    @Override
    public  List<THistoryOperateEntity> findList(THistoryOperateEntity tHistoryOperate) {
        return tHistoryOperateDao.findList(tHistoryOperate);
    }

	@Override
	public int queryTotal(Map<String, Object> map){
		return tHistoryOperateDao.queryTotal(map);
	}
	
	@Override
	public void save(THistoryOperateEntity tHistoryOperate){
		tHistoryOperateDao.save(tHistoryOperate);
	}
	
	@Override
	public void update(THistoryOperateEntity tHistoryOperate){
		tHistoryOperateDao.update(tHistoryOperate);
	}
	
	@Override
	public void delete(String operateId){
		tHistoryOperateDao.delete(operateId);
	}
	
	@Override
	public void deleteBatch(String[] operateIds){
		tHistoryOperateDao.deleteBatch(operateIds);
	}
	
}
