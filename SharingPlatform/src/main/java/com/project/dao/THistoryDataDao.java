package com.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;
import com.project.entity.THistoryDataEntity;

@Mapper
public interface THistoryDataDao extends BaseDao<THistoryDataEntity> {
    List<THistoryDataEntity> findALL();

    List<THistoryDataEntity> findList(THistoryDataEntity tHistoryData);
    
    void  insertHistory(Map<String, Object> map);
    
    List<THistoryDataEntity> findHistory(Map<String, Object> map);


}
