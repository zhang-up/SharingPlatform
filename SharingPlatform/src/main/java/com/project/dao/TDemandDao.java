package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.TDemandEntity;

@Mapper
public interface TDemandDao extends BaseDao<TDemandEntity> {
    List<TDemandEntity> findALL();

    List<TDemandEntity> findList(TDemandEntity tDemand);


}
