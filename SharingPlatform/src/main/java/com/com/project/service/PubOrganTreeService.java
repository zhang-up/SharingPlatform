package com.com.project.service;

import com.project.entity.PubOrganTreeEntity;

import java.util.List;
import java.util.Map;

public interface PubOrganTreeService {
	
	PubOrganTreeEntity queryObject(String id);
	List<PubOrganTreeEntity> queryList(Map<String, Object> map);
    List<PubOrganTreeEntity> findALL();
    List<PubOrganTreeEntity> findList(PubOrganTreeEntity pubOrganTree);
	int queryTotal(Map<String, Object> map);
	
	void save(PubOrganTreeEntity pubOrganTree);
	
	void update(PubOrganTreeEntity pubOrganTree);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
