package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.project.entity.TestPO;

public interface TestDAO {
	@Select("select * from student ")
	public List<TestPO> find();
}
