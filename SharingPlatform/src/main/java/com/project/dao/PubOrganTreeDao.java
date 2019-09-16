package com.project.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.PubOrganTreeEntity;

@Mapper
public interface PubOrganTreeDao extends BaseDao<PubOrganTreeEntity> {
    List<PubOrganTreeEntity> findALL();

    List<PubOrganTreeEntity> findList(PubOrganTreeEntity pubOrganTree);


}
