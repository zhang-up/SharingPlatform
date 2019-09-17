package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.TParameterEntity;

@Mapper
public interface TParameterDao extends BaseDao<TParameterEntity> {
    List<TParameterEntity> findALL();

    List<TParameterEntity> findList(TParameterEntity tParameter);


}
