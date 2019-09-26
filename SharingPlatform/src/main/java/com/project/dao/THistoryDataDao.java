package com.project.dao;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.apache.ibatis.annotations.Mapper;

import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;
import com.project.entity.THistoryDataEntity;
import com.project.info.TDemandStatisticInfo;
import com.project.info.THistoryDataInfo;
import com.project.info.THistoryStatisticInfo;

@Mapper
public interface THistoryDataDao extends BaseDao<THistoryDataEntity> {
    List<THistoryDataEntity> findALL();

    List<THistoryDataEntity> findList(THistoryDataEntity tHistoryData);
    
    void  insertHistory(THistoryDataEntity tHistoryDataEntity);
    
    List<THistoryDataEntity> findHistory(Map<String, Object> map);

    THistoryDataInfo findHistoryDetails(Map<String, Object> map);
    
    String findHistoryCode(String id);
    
    void changHistoryState(Map<String, Object> map);
    
    void changDraftHistory(THistoryDataEntity tHistoryData);
    

    //提供方统计
    List<THistoryStatisticInfo> historyStatisticPro(Map<String, Object> map);
    
    
   
    
 

}
