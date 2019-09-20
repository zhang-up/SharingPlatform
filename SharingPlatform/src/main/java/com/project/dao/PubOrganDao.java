package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.PubOrganEntity;
import com.project.info.PubOrganTreeInfo;
import com.project.param.PubOrganParam;

@Mapper
public interface PubOrganDao extends BaseDao<PubOrganEntity> {
    List<PubOrganEntity> findALL();

    List<PubOrganEntity> findList(PubOrganEntity pubOrgan);
    
  //根据parentCode查询所有相对应的子集
  	List<PubOrganTreeInfo> tree(PubOrganParam pubOrgan);

  	List<PubOrganTreeInfo> treeRoot();
}
