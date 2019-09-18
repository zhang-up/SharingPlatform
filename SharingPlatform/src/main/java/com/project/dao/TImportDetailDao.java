package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.TImportDetailEntity;

@Mapper
public interface TImportDetailDao extends BaseDao<TImportDetailEntity> {
    List<TImportDetailEntity> findALL();

    List<TImportDetailEntity> findList(TImportDetailEntity tImportDetail);


}
