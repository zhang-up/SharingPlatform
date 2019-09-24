package com.project.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.project.service.RcResourceService;
import com.project.dao.RcResourceDao;
import com.project.dao.TDemandDao;
import com.project.dao.TDemandResourceDao;
import com.project.entity.RcResourceEntity;
import com.project.entity.TDemandResourceEntity;
import com.project.info.RcResourceInfo;
import com.project.info.TDemandInfo;
import com.project.utils.StringUtil;
import com.project.utils.UUIDUtil;



@Service("rcResourceService")
public class RcResourceServiceImpl implements RcResourceService {
	@Autowired
	private RcResourceDao rcResourceDao;
	@Autowired
	private TDemandDao tDemandDao;
	@Autowired
	private TDemandResourceDao tDemandResourceDao;
	
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

	@Override
	public List<RcResourceInfo> matchingRes(String keyWord) {

		if(StringUtil.isNull(keyWord)){
			return new ArrayList<RcResourceInfo>();
		}
		
		String[] keyWs = keyWord.split("，");
		Map<String, Object> map = new HashMap<String,Object>();
		
		int nums = 1;
		for(String key : keyWs){
			if(nums>5){
				break;
			}
			map.put("keyWord"+nums, "%"+key+"%");
			nums++;
		}
		return rcResourceDao.matchingRes(map);
	}
	
	@Override
	public void matchingAllRes(String userId) {

		//查询全部需求清单
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("state", "00");
		param.put("apply", "yes");
		param.put("userId", userId);
		List<TDemandInfo> tdiList = tDemandDao.queryInfoList(param);
		for(TDemandInfo tdi : tdiList){
			int recoNums = tdi.getRecommendNums();
			if(recoNums < 1){//没有推荐过
				String keyWord = tdi.getKeyWord();
				if(!StringUtil.isNull(keyWord)){//有关键字
					String[] keyWs = keyWord.split("，");
					Map<String, Object> map = new HashMap<String,Object>();
					
					int nums = 1;
					for(String key : keyWs){
						if(nums>5){
							break;
						}
						map.put("keyWord"+nums, "%"+key+"%");
						nums++;
					}
					
					List<RcResourceInfo> rriList = rcResourceDao.matchingRes(map);
					String demandId = tdi.getDemandId();
					for(RcResourceInfo rr : rriList){
						TDemandResourceEntity tdre = new TDemandResourceEntity();
						tdre.setDResId(UUIDUtil.getUUID32());
						tdre.setResourceId(rr.getId());
						tdre.setDemandId(demandId);
						tdre.setState("0");
						tDemandResourceDao.save(tdre);
					}
				}
			}
		}
	}
	
}
