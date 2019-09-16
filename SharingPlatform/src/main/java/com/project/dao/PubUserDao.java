package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.PubUserEntity;

@Mapper
public interface PubUserDao extends BaseDao<PubUserEntity> {
    List<PubUserEntity> findALL();

    List<PubUserEntity> findList(PubUserEntity pubUser);


}
