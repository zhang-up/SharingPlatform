package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.TDemandDao;
import com.project.entity.TDemandEntity;
import com.project.info.DockTrialInfo;
import com.project.service.TDemandService;



@Service("tDemandService")
public class TDemandServiceImpl implements TDemandService {
	@Autowired
	private TDemandDao tDemandDao;
	
	@Override
	public TDemandEntity queryObject(String demandId){
		return tDemandDao.queryObject(demandId);
	}
	
	@Override
	public List<TDemandEntity> queryList(Map<String, Object> map){
		return tDemandDao.queryList(map);
	}

	@Override
	public  List<TDemandEntity> findALL(){
        return tDemandDao.findALL();
	}

    @Override
    public  List<TDemandEntity> findList(TDemandEntity tDemand) {
        return tDemandDao.findList(tDemand);
    }

	@Override
	public int queryTotal(Map<String, Object> map){
		return tDemandDao.queryTotal(map);
	}
	
	@Override
	public void save(TDemandEntity tDemand){
		tDemandDao.save(tDemand);
	}
	
	@Override
	public void update(TDemandEntity tDemand){
		tDemandDao.update(tDemand);
	}
	
	@Override
	public void delete(String demandId){
		tDemandDao.delete(demandId);
	}
	
	@Override
	public void deleteBatch(String[] demandIds){
		tDemandDao.deleteBatch(demandIds);
	}

	@Override
	public List<DockTrialInfo> dockingList(Map<String, Object> map) {
		return tDemandDao.queryInfoList(map);
	}
	
}
