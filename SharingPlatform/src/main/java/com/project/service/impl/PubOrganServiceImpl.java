package com.project.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.PubOrganDao;
import com.project.entity.PubOrganEntity;
import com.project.info.PubOrganTreeInfo;
import com.project.param.PubOrganParam;
import com.project.service.PubOrganService;
import com.project.utils.StringUtil;



@Service("pubOrganService")
public class PubOrganServiceImpl implements PubOrganService {
	@Autowired
	private PubOrganDao pubOrganDao;
	
	@Override
	public PubOrganEntity queryObject(String id){
		return pubOrganDao.queryObject(id);
	}
	
	@Override
	public List<PubOrganEntity> queryList(Map<String, Object> map){
		return pubOrganDao.queryList(map);
	}

	@Override
	public  List<PubOrganEntity> findALL(){
        return pubOrganDao.findALL();
	}

    @Override
    public  List<PubOrganEntity> findList(PubOrganEntity pubOrgan) {
        return pubOrganDao.findList(pubOrgan);
    }

	@Override
	public int queryTotal(Map<String, Object> map){
		return pubOrganDao.queryTotal(map);
	}
	
	@Override
	public void save(PubOrganEntity pubOrgan){
		pubOrganDao.save(pubOrgan);
	}
	
	@Override
	public void update(PubOrganEntity pubOrgan){
		pubOrganDao.update(pubOrgan);
	}
	
	@Override
	public void delete(String id){
		pubOrganDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		pubOrganDao.deleteBatch(ids);
	}

	@Override
	public List<PubOrganTreeInfo> tree(String parentCode) {
		if(StringUtil.isNull(parentCode)){
			return new ArrayList<PubOrganTreeInfo>();
		}
		PubOrganParam pubOrgan = new PubOrganParam();
		pubOrgan.setParentCode(parentCode);
		return pubOrganDao.tree(pubOrgan);
	}
	
	@Override
	public List<PubOrganTreeInfo> treeByKey(String name) {
		if(StringUtil.isNull(name)){
			return new ArrayList<PubOrganTreeInfo>();
		}
		PubOrganParam pubOrgan = new PubOrganParam();
		pubOrgan.setName("%"+name+"%");
		List<PubOrganTreeInfo> orList = crateTreeList(pubOrganDao.tree(pubOrgan), null);
		isOpenTrre(orList);
		return orList;
	}
	
	private void isOpenTrre(List<PubOrganTreeInfo> orList) {
		
		Map<String,String> pCodes = new HashMap<String,String>();
		for(PubOrganTreeInfo poti : orList){
			String pCode = poti.getParentCode();
			pCodes.put(pCode, pCode);
		}
		
		for(PubOrganTreeInfo poti : orList){
			String pCode = poti.getParentCode();
			if(!StringUtil.isNull(pCodes.get(pCode))){
				poti.setOpen(true);
			}
		}
		
	}
	
	//根据模糊搜索的节点查询树结构
	private List<PubOrganTreeInfo> crateTreeList(List<PubOrganTreeInfo> orList, Map<String,String> pCodes) {
		if(orList==null || orList.size()<1){
			return new ArrayList<PubOrganTreeInfo>();
		}
		
		if(pCodes == null){
			pCodes = new HashMap<String,String>();
		}
		for(PubOrganTreeInfo poti : orList){
			String pCode = poti.getParentCode();
			if(StringUtil.isNull(pCodes.get(pCode)) && !"#".equals(pCode)){
				pCodes.put(pCode, "no");
			}
		}
		for(String key : pCodes.keySet()){
			if("no".equals(pCodes.get(key))){
				PubOrganParam pubOrgan = new PubOrganParam();
				pubOrgan.setCode(key);
				List<PubOrganTreeInfo> reList = pubOrganDao.tree(pubOrgan);
				orList.addAll(reList);
				pCodes.put(key, "yes");
			}
		}
		
		boolean havePare = false;
		for(PubOrganTreeInfo poti : orList){
			String pCode = poti.getParentCode();
			if(StringUtil.isNull(pCodes.get(pCode)) && !"#".equals(pCode)){
				pCodes.put(pCode, "no");
				havePare = true;
			}
		}
		
		if(havePare){
			crateTreeList(orList, pCodes);
		}
		
		
		return orList;
	}
	
}
