package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.RcResourceEntity;

@Mapper
public interface RcResourceDao extends BaseDao<RcResourceEntity> {
    List<RcResourceEntity> findALL();

    List<RcResourceEntity> findList(RcResourceEntity rcResource);


}
