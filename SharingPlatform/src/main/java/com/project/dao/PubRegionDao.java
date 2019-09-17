package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.PubRegionEntity;

@Mapper
public interface PubRegionDao extends BaseDao<PubRegionEntity> {
    List<PubRegionEntity> findALL();

    List<PubRegionEntity> findList(PubRegionEntity pubRegion);


}
