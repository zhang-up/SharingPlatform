package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.project.dao.TImportDetailDao;
import com.project.entity.TImportDetailEntity;
import com.project.service.TImportDetailService;



@Service("tImportDetailService")
public class TImportDetailServiceImpl implements TImportDetailService {
	@Autowired
	private TImportDetailDao tImportDetailDao;
	
	@Override
	public TImportDetailEntity queryObject(String importDetailId){
		return tImportDetailDao.queryObject(importDetailId);
	}
	
	@Override
	public List<TImportDetailEntity> queryList(Map<String, Object> map){
		return tImportDetailDao.queryList(map);
	}

	@Override
	public  List<TImportDetailEntity> findALL(){
        return tImportDetailDao.findALL();
	}

    @Override
    public  List<TImportDetailEntity> findList(TImportDetailEntity tImportDetail) {
        return tImportDetailDao.findList(tImportDetail);
    }

	@Override
	public int queryTotal(Map<String, Object> map){
		return tImportDetailDao.queryTotal(map);
	}
	
	@Override
	public void save(TImportDetailEntity tImportDetail){
		tImportDetailDao.save(tImportDetail);
	}
	
	@Override
	public void update(TImportDetailEntity tImportDetail){
		tImportDetailDao.update(tImportDetail);
	}
	
	@Override
	public void delete(String importDetailId){
		tImportDetailDao.delete(importDetailId);
	}
	
	@Override
	public void deleteBatch(String[] importDetailIds){
		tImportDetailDao.deleteBatch(importDetailIds);
	}
	
}
