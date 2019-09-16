package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.THistoryOperateEntity;

@Mapper
public interface THistoryOperateDao extends BaseDao<THistoryOperateEntity> {
    List<THistoryOperateEntity> findALL();

    List<THistoryOperateEntity> findList(THistoryOperateEntity tHistoryOperate);


}
