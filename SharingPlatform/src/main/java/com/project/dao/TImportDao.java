package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.TImportEntity;

@Mapper
public interface TImportDao extends BaseDao<TImportEntity> {
    List<TImportEntity> findALL();

    List<TImportEntity> findList(TImportEntity tImport);


}
