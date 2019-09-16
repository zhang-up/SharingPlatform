package com.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.TDemandEntity;
import com.project.info.TDemandInfo;

@Mapper
public interface TDemandDao extends BaseDao<TDemandEntity> {
    List<TDemandEntity> findALL();

    List<TDemandEntity> findList(TDemandEntity tDemand);
    
    List<TDemandInfo> queryInfoList(Map<String, Object> map);


}
