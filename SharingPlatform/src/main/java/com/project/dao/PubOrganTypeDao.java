package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.PubOrganTypeEntity;

@Mapper
public interface PubOrganTypeDao extends BaseDao<PubOrganTypeEntity> {
    List<PubOrganTypeEntity> findALL();

    List<PubOrganTypeEntity> findList(PubOrganTypeEntity pubOrganType);


}
