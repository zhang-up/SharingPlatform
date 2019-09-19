package com.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.RcResourceEntity;
import com.project.info.RcResourceInfo;

@Mapper
public interface RcResourceDao extends BaseDao<RcResourceEntity> {
    List<RcResourceEntity> findALL();

    List<RcResourceEntity> findList(RcResourceEntity rcResource);

    List<RcResourceInfo> matchingRes(Map<String, Object> map);
}
