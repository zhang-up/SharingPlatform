package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.TDemandResourceEntity;

@Mapper
public interface TDemandResourceDao extends BaseDao<TDemandResourceEntity> {
    List<TDemandResourceEntity> findALL();

    List<TDemandResourceEntity> findList(TDemandResourceEntity tDemandResource);


    int deleteByDemand(Object demandId);

}
