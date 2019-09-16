package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.THistoryDataEntity;

@Mapper
public interface THistoryDataDao extends BaseDao<THistoryDataEntity> {
    List<THistoryDataEntity> findALL();

    List<THistoryDataEntity> findList(THistoryDataEntity tHistoryData);


}
