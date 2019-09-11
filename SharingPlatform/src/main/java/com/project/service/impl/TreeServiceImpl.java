package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.TreeDao;
import com.project.po.TreePo;
import com.project.service.TreeService;
@Service
@Transactional
public class TreeServiceImpl implements TreeService{
	@Autowired
	private TreeDao treeDao;
	@Override
	public List<TreePo> tree(String parentCode) {		
		return treeDao.tree(parentCode);
	}

}
