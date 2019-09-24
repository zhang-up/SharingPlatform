package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.com.project.service.TImportService;
import com.project.dao.TDemandDao;
import com.project.dao.TDemandOperateDao;
import com.project.dao.TImportDao;
import com.project.dao.TImportDetailDao;
import com.project.entity.TDemandEntity;
import com.project.entity.TDemandOperateEntity;
import com.project.entity.TImportDetailEntity;
import com.project.entity.TImportEntity;



@Service("tImportService")
public class TImportServiceImpl implements TImportService {
	@Autowired
	private TImportDao tImportDao;
	
	@Autowired
	private TDemandDao tDemandDao;
	
	@Autowired
	private TDemandOperateDao tDemandOperateDao;
	
	@Autowired
	private TImportDetailDao tImportDetailDao;
	
	@Override
	public TImportEntity queryObject(String importId){
		return tImportDao.queryObject(importId);
	}
	
	@Override
	public List<TImportEntity> queryList(Map<String, Object> map){
		return tImportDao.queryList(map);
	}

	@Override
	public  List<TImportEntity> findALL(){
        return tImportDao.findALL();
	}

    @Override
    public  List<TImportEntity> findList(TImportEntity tImport) {
        return tImportDao.findList(tImport);
    }

	@Override
	public int queryTotal(Map<String, Object> map){
		return tImportDao.queryTotal(map);
	}
	
	@Override
	public void save(TImportEntity tImport){
		tImportDao.save(tImport);
	}
	
	@Override
	public void update(TImportEntity tImport){
		tImportDao.update(tImport);
	}
	
	@Override
	public void delete(String importId){
		tImportDao.delete(importId);
	}
	
	@Override
	public void deleteBatch(String[] importIds){
		tImportDao.deleteBatch(importIds);
	}

	@Override
	public void saveInport(TImportEntity tie, List<TDemandEntity> tedList, List<TDemandOperateEntity> tdoeList,
			List<TImportDetailEntity> tideList) {
		
		tImportDao.save(tie);
		
		for(TDemandEntity tde : tedList){
			tDemandDao.save(tde);
		}
		
		for(TDemandOperateEntity tdoe : tdoeList){
			tDemandOperateDao.save(tdoe);
		}
		
		for(TImportDetailEntity tide : tideList){
			tImportDetailDao.save(tide);
		}
	}
	
}
