package com.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.THistoryOperateEntity;

@Mapper
public interface THistoryOperateDao extends BaseDao<THistoryOperateEntity> {
    List<THistoryOperateEntity> findALL();

    List<THistoryOperateEntity> findList(THistoryOperateEntity tHistoryOperate);
    
    void insertHistory_operate(THistoryOperateEntity tho);
  
    void deleteByData(String id);
    
    THistoryOperateEntity findOperateRes(Map<String, Object> map);
    
    
    void changDraftHistoryOperate(THistoryOperateEntity tho);
    
    THistoryOperateEntity findOperate(Map<String, Object> map);


}
