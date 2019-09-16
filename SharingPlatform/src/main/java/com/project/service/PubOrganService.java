package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.entity.PubOrganEntity;
import com.project.info.PubOrganTreeInfo;

public interface PubOrganService {
	
	PubOrganEntity queryObject(String id);
	List<PubOrganEntity> queryList(Map<String, Object> map);
    List<PubOrganEntity> findALL();
    List<PubOrganEntity> findList(PubOrganEntity pubOrgan);
	int queryTotal(Map<String, Object> map);
	
	void save(PubOrganEntity pubOrgan);
	
	void update(PubOrganEntity pubOrgan);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	//根据parentCode查询所有相对应的子集
	List<PubOrganTreeInfo> tree(String parentCode);
	
	//根据关键字模糊搜索树
	List<PubOrganTreeInfo> treeByKey(String name);
}
