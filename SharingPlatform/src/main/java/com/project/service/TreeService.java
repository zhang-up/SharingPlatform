package com.project.service;

import java.util.List;

import com.project.po.TreePo;

public interface TreeService {
	//根据parentCode查询所有相对应的子集
	List<TreePo> tree(String parentCode);
}
